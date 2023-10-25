package com.mycompany.uniburguerretaguarda.gateway;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mycompany.uniburguerretaguarda.dto.LoginDTO;
import com.mycompany.uniburguerretaguarda.model.Produto;
import com.mycompany.uniburguerretaguarda.model.Pedido;
import com.mycompany.uniburguerretaguarda.model.PedidoItem;
import java.util.Arrays;
import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class APIGateway {
    
    private final String API = "https://gustakx36.pythonanywhere.com";
    private final RestTemplate rest = new RestTemplate();

    public List<Produto> getProdutos() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<List<Produto>> response = rest.exchange(API + "/product",
                HttpMethod.GET, requestEntity, new ParameterizedTypeReference<List<Produto>>() {
        });

        return response.getBody();
    }
    
    public List<Pedido> getPedidos() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<List<Pedido>> response = rest.exchange(API + "/orders",
                HttpMethod.GET, requestEntity, new ParameterizedTypeReference<List<Pedido>>() {
        });

        return response.getBody();
    }
    
    public List<PedidoItem> getPedidoItens(int pIdPedido) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<List<PedidoItem>> response = rest.exchange(API + "/orderItem/" + pIdPedido,
                HttpMethod.GET, requestEntity, new ParameterizedTypeReference<List<PedidoItem>>() {
        });

        return response.getBody();
    }
    
    public HttpStatusCode putPedido(Pedido order) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<Pedido> requestEntity = new HttpEntity<>(order, headers);

        ResponseEntity response = rest.exchange(API + "/orders/" + order.getId(),
                HttpMethod.PUT, requestEntity, new ParameterizedTypeReference<Pedido>() {
        });
        
        return response.getStatusCode();
    }
    
    public HttpStatusCode verificaLogin(LoginDTO login) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
	headers.add("Content-Type", "application/json");
        
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        ow.writeValueAsString(login);
        
        HttpEntity<LoginDTO> requestEntity = new HttpEntity<>(login, headers);

        ResponseEntity<LoginDTO> response = rest.postForEntity(API + "/verifyLogin", requestEntity, LoginDTO.class);
        response.getBody();
        
        return response.getStatusCode();
    }
    
}