package com.hellys.financeiro.service;

import com.hellys.financeiro.model.TipoTransacao;
import com.hellys.financeiro.model.Transacao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FinanceiroService {
    private List<Transacao> transacoes;

    public FinanceiroService() {
        this.transacoes = new ArrayList<>();
    }

    public void adicionarTransacao(Transacao transacao) {
        transacoes.add(transacao);
    }

    public void listarTransacoes() {
        for (Transacao transacao : transacoes) {
            System.out.println(transacao);
            System.out.println("----------------------------");
        }
    }

    public double calcularSaldo() {
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

    public void removerTransacao(int id) {
        Transacao transacao = buscarPorId(id);

        if (transacao == null) {
            System.out.println("Fail: ID nao econtrado");
            return;
        }

        System.out.println("Transação: \n" +transacao+ "\nRemovida com suceso");
        transacoes.remove(transacao);
    }

    public void editarTransacao(int id) {  // colocar esdcolha na main
        Scanner input = new Scanner(System.in);

        Transacao transacao = buscarPorId(id);

        if (transacao == null) {
            System.out.println("Fail: ID nao econtrado");
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

        switch (escolha) {
            case 0:
                System.out.println("Canselando edisão");
                return;
            case 1:
                System.out.println("Digite a nova descrição");
                String descricao = input.nextLine();
                transacao.setDescricao(descricao);
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
                System.out.println("Fail: comando nao encontrado");
                break;
        }
    }

    public void editarDescricao(int id, String descricao) {
        Transacao transacao = buscarPorId(id);

        if (transacao == null) {
            System.out.println("Fail: ID nao econtrado");
            return;
        }

        transacao.setDescricao(descricao);
    }

    private Transacao buscarPorId(int id) {
        for (Transacao transacao : transacoes) {
            if (id == transacao.getId()) {
                return transacao;
            }
        }
        return null;
    }
}
