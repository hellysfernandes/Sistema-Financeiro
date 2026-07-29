package com.hellys.financeiro.model;

import java.time.LocalDate;

public class Transacao {
    private static int proximoId = 1;

    private int id;
    private String descricao;
    private double valor;
    private LocalDate data;
    private TipoTransacao tipo;

    public Transacao(String descricao,  double valor, TipoTransacao tipo) {
        this.id = proximoId;
        proximoId++;
        this.descricao = descricao;
        this.valor = valor;
        this.data = LocalDate.now();
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValor() {
        return valor;
    }

    public LocalDate getData() {
        return data;
    }

    public TipoTransacao getTipo() {
        return tipo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setTipo(TipoTransacao tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "ID: " +id+ "\nDescrição: " +descricao+ "\nValor: " +valor+ "\nTipo: " +tipo+ "\nData: " +data;
    }
}
