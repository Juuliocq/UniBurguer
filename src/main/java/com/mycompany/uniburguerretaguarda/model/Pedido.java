package com.mycompany.uniburguerretaguarda.model;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class Pedido {

    private int id;
    private boolean finalizado;
    private String nome_cliente;
    private String data_hora;
    private List<PedidoItem> pedidoItens = new ArrayList<>();
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }

    public String getNome_cliente() {
        return nome_cliente;
    }

    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }

    public String getData_hora() {
        return data_hora;
    }

    public void setData_hora(String data_hora) {
        this.data_hora = data_hora;
    }

    public void setPedidoItens(List<PedidoItem> pedidoItens) {
        this.pedidoItens = pedidoItens;
    }
    
    public List<PedidoItem> getPedidoItens() {
        return pedidoItens;
    }
    
    public int getQtdPedidoItens() {
        return pedidoItens.size();
    }
    
    public String getValorTotalPedido() {
        double valorTotal = pedidoItens.stream().collect(Collectors.summingDouble(PedidoItem::getPreco));

        Locale locale = new Locale("pt", "BR");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        return currencyFormatter.format(valorTotal);
    }

}
  