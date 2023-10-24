package com.mycompany.uniburguerretaguarda.model;

import java.math.BigDecimal;

public class Produto {
    
    private int id;
    private String nome;
    private String descricao;
    private int tipo_produto;
    private String imagem;
    private String preco;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getTipo_produto() {
        return tipo_produto;
    }

    public void setTipo_produto(int tipo_produto) {
        this.tipo_produto = tipo_produto;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }
    
}
