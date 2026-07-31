package com.hellys.financeiro.service;

import com.hellys.financeiro.model.TipoTransacao;
import com.hellys.financeiro.model.Transacao;

import java.util.ArrayList;
import java.util.List;

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

    public void editarDescricao(int id, String descricao) {
        Transacao transacao = buscarPorId(id);

        if (transacao == null) {
            System.out.println("Fail: ID nao econtrado");
            return;
        }

        transacao.setDescricao(descricao);
        System.out.println("Descrição alterado com suceso");
    }

    public void editarValor(int id, double valor) {
        Transacao transacao = buscarPorId(id);

        if (transacao == null) {
            System.out.println("Fail: ID nao econtrado");
            return;
        }

        transacao.setValor(valor);
        System.out.println("Valor alterado com suceso");
    }

    public void editarTipo(int id) {
        Transacao transacao = buscarPorId(id);

        if (transacao == null) {
            System.out.println("Fail: ID nao econtrado");
            return;
        }

        if (transacao.getTipo() == TipoTransacao.RECEITA) {
            transacao.setTipo(TipoTransacao.DESPESA);
        } else {
            transacao.setTipo(TipoTransacao.RECEITA);
        }

        System.out.println("Troca de tipo realizado com suceso");
    }

    public Transacao buscarPorId(int id) {
        for (Transacao transacao : transacoes) {
            if (id == transacao.getId()) {
                return transacao;
            }
        }
        return null;
    }
}
