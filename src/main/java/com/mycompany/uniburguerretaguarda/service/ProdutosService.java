/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uniburguerretaguarda.service;

import com.mycompany.uniburguerretaguarda.gateway.APIGateway;
import com.mycompany.uniburguerretaguarda.model.Produto;
import java.util.List;

/**
 *
 * @author zeski
 */
public class ProdutosService {

    private final APIGateway api = new APIGateway();

    public List<Produto> getProdutos() {
        return api.getProdutos();
    }
}
