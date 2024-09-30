package com.udemy.exercicio_leitores_spring_batch.domain;

import java.util.ArrayList;
import java.util.List;

public class Orcamento {

    private Long id;
    private String nome;
    private String total;
    private List<ItemsDoOrcamento> itemsDoOrcamentoList = new ArrayList<>();

    public Orcamento() {
    }

    public Orcamento(Long id, String nome, String total, List<ItemsDoOrcamento> itemsDoOrcamentoList) {
        this.id = id;
        this.nome = nome;
        this.total = total;
        this.itemsDoOrcamentoList = itemsDoOrcamentoList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<ItemsDoOrcamento> getItemsDoOrcamentoList() {
        return itemsDoOrcamentoList;
    }

    public void setItemsDoOrcamentoList(List<ItemsDoOrcamento> itemsDoOrcamentoList) {
        this.itemsDoOrcamentoList = itemsDoOrcamentoList;
    }

    @Override
    public String toString() {
        return "Orcamento{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", total=" + total +
                ", itemsDoOrcamentoList=" + itemsDoOrcamentoList +
                '}';
    }
}
