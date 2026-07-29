package com.hellys.financeiro;

import com.hellys.financeiro.model.TipoTransacao;
import com.hellys.financeiro.model.Transacao;
import com.hellys.financeiro.service.FinanceiroService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Transacao transacao;
        FinanceiroService financeiroService = new FinanceiroService();

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
                case 0:
                    System.out.println("saindo do sistema");
                    break;

                case 1:
                    System.out.println("Digite a descrição");
                    String receitaDescricao = input.nextLine();

                    System.out.println("Digite o valor");
                    double receitaValor = input.nextDouble();
                    input.nextLine();

                    transacao = new Transacao(receitaDescricao, receitaValor, TipoTransacao.RECEITA);
                    financeiroService.adicionarTransacao(transacao);

                    break;

                case 2:
                    System.out.println("Digite a descrição");
                    String dispesaDescricao = input.nextLine();

                    System.out.println("Digite o valor");
                    double dispesaValor = input.nextDouble();
                    input.nextLine();

                    transacao = new Transacao(dispesaDescricao, dispesaValor, TipoTransacao.DESPESA);
                    financeiroService.adicionarTransacao(transacao);

                    break;
                case 3:
                    financeiroService.listarTransacoes();
                    break;
                case 4:
                    System.out.println("Saldo: R$ " + financeiroService.calcularSaldo());
                    break;
                case 5:
                    financeiroService.listarTransacoes();
                    System.out.println("escolha o ID de qual transação deseja deletar");
                    int deletId = input.nextInt();

                    financeiroService.removerTransacao(deletId);
                    break;
                case 6:
                    financeiroService.listarTransacoes();
                    System.out.println("escolha o ID de qual transação deseja editar");
                    int editId = input.nextInt();

                    System.out.println("===== Editar Transação =====");
                    System.out.println(financeiroService.);
                    System.out.println("o que deseja editar");

                    System.out.println("1 - Descrição");
                    System.out.println("2 - Valor");
                    System.out.println("3 - Tipo");
                    System.out.println("0 - Cancelar");

                    int edicao = input.nextInt();

                    switch (edicao) {
                        case 0:
                            System.out.println("Canselando edisão");
                            break;
                        case 1:
                            System.out.println("Digite a nova descrição");
                            String descricao = input.nextLine();
                            financeiroService.editarDescricao(editId, descricao);

                            break;
                        case 2:
                            System.out.println("Digite o novo valor");
                            double valor = input.nextDouble();
                            transacao.setValor(valor);
                            break;
                        case 3:
                            System.out.println("Trocando tipo");
                            if (transacao.getTipo() == TipoTransacao.RECEITA) {
                                transacao.setTipo(TipoTransacao.DESPESA);
                            } else {
                                transacao.setTipo(TipoTransacao.RECEITA);
                            }
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    System.out.println("Fail: comando invalido");
                    break;
            }

        } while (escolha != 0);

    }
}