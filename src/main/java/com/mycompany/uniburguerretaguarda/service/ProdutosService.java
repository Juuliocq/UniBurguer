/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uniburguerretaguarda.service;

import com.mycompany.uniburguerretaguarda.gateway.APIGateway;
import com.mycompany.uniburguerretaguarda.model.Categoria;
import com.mycompany.uniburguerretaguarda.model.Produto;
import java.util.List;
import javax.swing.JOptionPane;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class ProdutosService {

    private final APIGateway api = new APIGateway();

    public List<Produto> getProdutos() {
        return api.getProdutos();
    }
    
    public Produto getProduto(int pIdProduto) {
        return api.getProduto(pIdProduto);
    }
    
    public void salvar(Produto produto) throws Exception {
//        validaCategoria(categoria);
        
        if (produto.getId() > 0) {
            alteraProduto(produto);
        } else {
            criaProduto(produto);
        }
    }
    
    private void criaProduto(Produto produto) throws Exception {
        HttpStatusCode statusRequisicao = api.postProduto(produto);
        
        if (statusRequisicao.equals(HttpStatus.ACCEPTED)) {
            JOptionPane.showMessageDialog(null, "Criado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao criar o Produto!");
            throw new Exception();
        }
    }
    
    private void alteraProduto(Produto produto) throws Exception {
        HttpStatusCode statusRequisicao = api.putProduto(produto);
        
        if (statusRequisicao.equals(HttpStatus.ACCEPTED)) {
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } else if (statusRequisicao.equals(HttpStatus.NO_CONTENT)) {
            JOptionPane.showMessageDialog(null, "Produto não encontrado!");
            throw new Exception();
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao salvar o Produto!");
            throw new Exception();
        }
    }
}
