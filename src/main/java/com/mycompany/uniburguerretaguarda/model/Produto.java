package com.mycompany.uniburguerretaguarda.model;

import java.math.BigDecimal;

public class Produto {
    
    private String nome;
    private String descricao;
    private BigDecimal preco;

    public Produto(String nome, String descricao, BigDecimal preco) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }
    
}
