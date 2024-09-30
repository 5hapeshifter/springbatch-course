package com.udemy.exercicio_leitores_spring_batch.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ItemsDoOrcamento {

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date data;
    private String nome;
    private String valorUnitario;

    public ItemsDoOrcamento() {
    }

    public ItemsDoOrcamento(Date data, String nome, String valorUnitario) {
        this.data = data;
        this.nome = nome;
        this.valorUnitario = valorUnitario;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(String valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    @Override
    public String toString() {
        return "ItemsDoOrcamento{" +
                "data=" + data +
                ", nome='" + nome + '\'' +
                ", valorUnitario=" + valorUnitario +
                '}';
    }
}
