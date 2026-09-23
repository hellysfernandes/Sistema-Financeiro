package com.hellys.financeiro.repository;

import com.hellys.financeiro.database.ConnectionFactory;
import com.hellys.financeiro.model.TipoTransacao;
import com.hellys.financeiro.model.Transacao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransacaoRepository {

    public void criarTabela() throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS transacoes (
                id INTEGER PRIMARY KEY,
                descricao TEXT NOT NULL,
                valor REAL NOT NULL,
                data TEXT NOT NULL,
                tipo TEXT NOT NULL
                )
                """;

        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }

    public void salvar(Transacao transacao) throws SQLException {
        String sql = "INSERT INTO transacoes (descricao, valor, data, tipo) VALUES (?,?,?,?)";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, transacao.getDescricao());
            pstmt.setDouble(2, transacao.getValor());
            pstmt.setString(3, transacao.getData().toString());
            pstmt.setString(4, transacao.getTipo().name());

            pstmt.executeUpdate();
        }
    }

    public List<Transacao> listar() throws SQLException {
        String sql = "SELECT * FROM transacoes";

        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(sql)) {

            List<Transacao> transacaos = new ArrayList<>();
            Transacao transacao;

            while (result.next()) {

                int id = result.getInt("id");
                String descricao = result.getString("descricao");
                double valor = result.getDouble("valor");
                LocalDate data = LocalDate.parse(result.getString("data"));
                String tipo = result.getString("tipo");

                TipoTransacao tipoTransacao = TipoTransacao.valueOf(tipo);

                transacao = new Transacao(id, descricao, valor, data, tipoTransacao);

                transacaos.add(transacao);
            }

            return transacaos;
        }
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM transacoes WHERE id = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int linhasAfetadas = pstmt.executeUpdate();

            if (linhasAfetadas == 0) {
                System.out.println("Fail: id não encontrado");
                return;
            }

            System.out.println("Transação com o id: " +id+ " deletado com suceso");
        }
    }

    public void editarDescricao(int id, String descricao) throws SQLException {
        String sql = "UPDATE transacoes SET descricao = ?  WHERE id = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1,descricao);
            pstmt.setInt(2, id);

            int linhasAfetadas = pstmt.executeUpdate();

            if (linhasAfetadas == 0) {
                System.out.println("id não encontrado");
                return;
            }

            System.out.println("descrição alterado para: " +descricao+ " para transação com id: " +id);
        }
    }

    public void editarValor(int id, double valor) throws SQLException {
        String sql = "UPDATE transacoes SET valor = ? WHERE id = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setDouble(1, valor);
            pstmt.setInt(2, id);

            int linhasAfetadas = pstmt.executeUpdate();

            if (linhasAfetadas == 0) {
                System.out.println("Fail: id não encontrado");
                return;
            }

            System.out.println("Valor alterado para: " +valor+ " para transação com id: " +id);
        }
    }

    public void editarTipo(int id) throws SQLException {
        String sqlConsulta = "SELECT tipo FROM transacoes WHERE id = ?";
        String sqlInsert = "UPDATE transacoes SET tipo = ? WHERE id = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement pstmt1 = connection.prepareStatement(sqlConsulta);
             PreparedStatement pstmt2 = connection.prepareStatement(sqlInsert)) {

            pstmt1.setInt(1, id);

            try (ResultSet result = pstmt1.executeQuery()) {
                if (!result.next()) {
                    System.out.println("Fail: id não encontrado");
                    return;
                }

                String tipo = result.getString("tipo");

                if (tipo.equals("RECEITA")) {
                    pstmt2.setString(1, "DESPESA");
                } else {
                    pstmt2.setString(1, "RECEITA");
                }

                pstmt2.setInt(2, id);
                pstmt2.executeUpdate();

                System.out.println("Tipo alterado com sucesso");
            }
        }
    }

    public Transacao buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM transacoes WHERE id = ?";
        Transacao transacao;

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet result = pstmt.executeQuery();) {
                if (!result.next()) {
                    System.out.println("id não encontrado");
                    return null;
                }

                id = result.getInt("id");
                String descricao = result.getString("descricao");
                double valor = result.getDouble("valor");
                LocalDate data = LocalDate.parse(result.getString("data"));
                String tipo = result.getString("tipo");

                TipoTransacao tipoTransacao = TipoTransacao.valueOf(tipo);

                return new Transacao(id, descricao, valor, data, tipoTransacao);
            }
        }
    }
}
