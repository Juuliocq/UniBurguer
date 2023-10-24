package com.mycompany.uniburguerretaguarda.gateway;

import com.mycompany.uniburguerretaguarda.Login;
import com.mycompany.uniburguerretaguarda.model.Produto;
import com.mycompany.uniburguerretaguarda.model.Orders;
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

    public List<Login> getLogins() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<List<Login>> response = rest.exchange(API + "/login",
                HttpMethod.GET, requestEntity, new ParameterizedTypeReference<List<Login>>() {
        });

        return response.getBody();
    }
    
    public List<Orders> getOrders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<List<Orders>> response = rest.exchange(API + "/orders",
                HttpMethod.GET, requestEntity, new ParameterizedTypeReference<List<Orders>>() {
        });

        return response.getBody();
    }
    
    public HttpStatusCode putOrder(Orders order) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<Orders> requestEntity = new HttpEntity<>(order, headers);

        ResponseEntity response = rest.exchange(API + "/orders/" + order.getId(),
                HttpMethod.PUT, requestEntity, new ParameterizedTypeReference<Orders>() {
        });
        
        return response.getStatusCode();
    }
    
}