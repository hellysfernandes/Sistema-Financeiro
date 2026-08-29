package com.hellys.financeiro;

import com.hellys.financeiro.model.TipoTransacao;
import com.hellys.financeiro.model.Transacao;
import com.hellys.financeiro.repository.TransacaoRepository;
import com.hellys.financeiro.service.FinanceiroService;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner input = new Scanner(System.in);

        FinanceiroService financeiroService = new FinanceiroService();

        try {
            TransacaoRepository transacaoRepository = new TransacaoRepository();
            transacaoRepository.criarTabela();
        } catch (SQLException e) {
            System.out.println("Fail erro ao criar tabela");
        }

        int escolha;

        do {
            System.out.println("========================");
            System.out.println(" SISTEMA FINANCEIRO ");
            System.out.println("========================");
            System.out.println("1. Adicionar receita");
            System.out.println("2. Adicionar despesa");
            System.out.println("3. Listar transações");
            System.out.println("4. Mostrar saldo");
            System.out.println("5. Remover transação");
            System.out.println("6. Editar transação");
            System.out.println("0. Sair");

            escolha = input.nextInt();
            input.nextLine();

            switch (escolha) {
                case 0 -> System.out.println("saindo do sistema");
                case 1 -> cadastrarTransacao(input, financeiroService, TipoTransacao.RECEITA);
                case 2 -> cadastrarTransacao(input, financeiroService, TipoTransacao.DESPESA);
                case 3 -> financeiroService.listarTransacoes();
                case 4 -> System.out.println("Saldo: R$ " + financeiroService.calcularSaldo());
                case 5 -> removerTransacao(input, financeiroService);
                case 6 -> editarTransacao(input, financeiroService);
                default -> System.out.println("Fail: comando invalido");
            }
        } while (escolha != 0);
    }

    private static void cadastrarTransacao(Scanner input, FinanceiroService financeiroService, TipoTransacao tipo) throws SQLException {
        System.out.print("Digite a descrição: ");
        String descricao = input.nextLine();

        System.out.print("Digite o valor: ");
        double valor = input.nextDouble();
        input.nextLine();

        if (valor <= 0) {
            System.out.println("valor negatico não sao permitidos");
            return;
        }

        Transacao transacao = new Transacao(descricao, valor, tipo);
        financeiroService.adicionarTransacao(transacao);
    }

    private static void removerTransacao(Scanner input, FinanceiroService financeiroService) throws SQLException {
        financeiroService.listarTransacoes();
        System.out.print("escolha o ID de qual transação deseja deletar: ");
        int id = input.nextInt();

        financeiroService.removerTransacao(id);
    }

    private static void editarTransacao(Scanner input, FinanceiroService financeiroService) throws SQLException {
        financeiroService.listarTransacoes();
        System.out.print("escolha o ID de qual transação deseja editar: ");
        int id = input.nextInt();
        input.nextLine();

        Transacao transacao = financeiroService.buscarPorId(id);

        if (transacao == null) {
            return;
        }

        System.out.println("===== Editar Transação =====");
        System.out.println(transacao);
        System.out.println("o que deseja editar");

        System.out.println("1 - Descrição");
        System.out.println("2 - Valor");
        System.out.println("3 - Tipo");
        System.out.println("0 - Cancelar");

        int escolha = input.nextInt();
        input.nextLine();

        switch (escolha) {
            case 0:
                System.out.println("Canselando edisão");
                break;
            case 1:
                System.out.print("Digite a nova descrição: ");
                String descricao = input.nextLine();
                financeiroService.editarDescricao(id, descricao);

                break;
            case 2:
                System.out.print("Digite o novo valor: ");
                double valor = input.nextDouble();
                input.nextLine();
                financeiroService.editarValor(id, valor);

                break;
            case 3:
                System.out.println("Trocando tipo");
                financeiroService.editarTipo(id);

                break;
            default:
                System.out.println("Fail: escolha nao encontrada");
                break;
        }
    }
}