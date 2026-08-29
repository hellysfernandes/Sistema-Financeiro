package com.hellys.financeiro.service;

import com.hellys.financeiro.model.TipoTransacao;
import com.hellys.financeiro.model.Transacao;
import com.hellys.financeiro.repository.TransacaoRepository;

import java.sql.SQLException;
import java.util.List;

public class FinanceiroService {
    private TransacaoRepository repository;
    
    public FinanceiroService() {
        this.repository = new TransacaoRepository();
    }

    public void adicionarTransacao(Transacao transacao) throws SQLException {
        repository.salvar(transacao);
    }

    public void listarTransacoes() throws SQLException {
        List<Transacao> transacoes = repository.listar();

        for (Transacao transacao : transacoes) {
            System.out.println(transacao);
            System.out.println("----------------------------");
        }
    }

    public double calcularSaldo() throws SQLException {
        List<Transacao> transacoes = repository.listar();
        double saldo = 0;

        for (Transacao transacao : transacoes) {
            if (transacao.getTipo() == TipoTransacao.RECEITA) {
                saldo += transacao.getValor();
            } else {
                saldo -= transacao.getValor();
            }
        }

        return saldo;
    }

    public void removerTransacao(int id) throws SQLException {
        repository.deletar(id);
    }

    public void editarDescricao(int id, String descricao) throws SQLException {
        repository.editarDescricao(id, descricao);
    }

    public void editarValor(int id, double valor) throws SQLException {
        repository.editarValor(id, valor);
    }

    public void editarTipo(int id) throws SQLException {
        repository.editarTipo(id);
    }

    public Transacao buscarPorId(int id) throws SQLException {
       return repository.buscarPorId(id);
    }
}
