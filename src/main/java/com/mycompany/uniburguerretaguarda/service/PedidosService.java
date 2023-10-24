package com.mycompany.uniburguerretaguarda.service;

import com.mycompany.uniburguerretaguarda.gateway.APIGateway;
import com.mycompany.uniburguerretaguarda.model.Orders;
import com.mycompany.uniburguerretaguarda.model.Pedido;
import com.mycompany.uniburguerretaguarda.model.Produto;
import java.util.List;
import javax.swing.JOptionPane;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class PedidosService {
    
    private final APIGateway api = new APIGateway();
    
    public List<Orders> getOrders() {
        return api.getOrders();
    }
    
    public void putOrder(Orders order) throws Exception {
        HttpStatusCode statusRequisicao = api.putOrder(order);
        
        if (statusRequisicao.equals(HttpStatus.ACCEPTED)) {
            JOptionPane.showMessageDialog(null, "Pedido Finalizado!");
        } else if (statusRequisicao.equals(HttpStatus.NO_CONTENT)) {
            JOptionPane.showMessageDialog(null, "Pedido não encontrado!");
            throw new Exception();
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao finalizar o pedido!");
            throw new Exception();
        }
    }
    
}
