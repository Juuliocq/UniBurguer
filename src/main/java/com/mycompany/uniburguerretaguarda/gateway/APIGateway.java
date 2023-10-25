package com.mycompany.uniburguerretaguarda.gateway;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mycompany.uniburguerretaguarda.dto.LoginDTO;
import com.mycompany.uniburguerretaguarda.model.Categoria;
import com.mycompany.uniburguerretaguarda.model.Produto;
import com.mycompany.uniburguerretaguarda.model.Pedido;
import com.mycompany.uniburguerretaguarda.model.PedidoItem;
import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class APIGateway {

    private final String API = "https://gustakx36.pythonanywhere.com";
    private final RestTemplate rest = new RestTemplate();

    private HttpHeaders getHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return headers;
    }

    public List<Produto> getProdutos() {
        HttpHeaders headers = getHeader();

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<List<Produto>> response = rest.exchange(API + "/product",
                HttpMethod.GET, requestEntity, new ParameterizedTypeReference<List<Produto>>() {
        });

        return response.getBody();
    }

    public List<Pedido> getPedidos() {
        HttpHeaders headers = getHeader();

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<List<Pedido>> response = rest.exchange(API + "/orders",
                HttpMethod.GET, requestEntity, new ParameterizedTypeReference<List<Pedido>>() {
        });

        return response.getBody();
    }

    public List<PedidoItem> getPedidoItens(int pIdPedido) {
        HttpHeaders headers = getHeader();

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<List<PedidoItem>> response = rest.exchange(API + "/orderItem/" + pIdPedido,
                HttpMethod.GET, requestEntity, new ParameterizedTypeReference<List<PedidoItem>>() {
        });

        return response.getBody();
    }

    public HttpStatusCode putPedido(Pedido order) {
        HttpHeaders headers = getHeader();

        HttpEntity<Pedido> requestEntity = new HttpEntity<>(order, headers);

        ResponseEntity response = rest.exchange(API + "/orders/" + order.getId(),
                HttpMethod.PUT, requestEntity, new ParameterizedTypeReference<Pedido>() {
        });

        return response.getStatusCode();
    }

    public HttpStatusCode verificaLogin(LoginDTO login) throws JsonProcessingException {
        HttpHeaders headers = getHeader();

        HttpEntity<LoginDTO> requestEntity = new HttpEntity<>(login, headers);
        ResponseEntity<LoginDTO> response = rest.postForEntity(API + "/verifyLogin", requestEntity, LoginDTO.class);

        return response.getStatusCode();
    }

    public List<Categoria> getCategorias() {
        HttpHeaders headers = getHeader();

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<List<Categoria>> response = rest.exchange(API + "/type",
                HttpMethod.GET, requestEntity, new ParameterizedTypeReference<List<Categoria>>() {
        });

        return response.getBody();
    }

    public HttpStatusCode putCategoria(Categoria categoria) {
        HttpHeaders headers = getHeader();

        HttpEntity<Categoria> requestEntity = new HttpEntity<>(categoria, headers);
        ResponseEntity response = rest.exchange(API + "/type/" + categoria.getId(),
                HttpMethod.PUT, requestEntity, new ParameterizedTypeReference<Categoria>() {
        });

        return response.getStatusCode();
    }

    public HttpStatusCode postCategoria(Categoria categoria) throws JsonProcessingException {
        HttpHeaders headers = getHeader();

        HttpEntity<Categoria> requestEntity = new HttpEntity<>(categoria, headers);
        ResponseEntity<Categoria> response = rest.postForEntity(API + "/type", requestEntity, Categoria.class);

        return response.getStatusCode();
    }

    public HttpStatusCode deleteCategoria(Categoria categoria) throws JsonProcessingException {
        HttpHeaders headers = getHeader();

        HttpEntity<Categoria> requestEntity = new HttpEntity<>(categoria, headers);

        try {
            ResponseEntity response = rest.exchange(API + "/type/" + categoria.getId(),
                    HttpMethod.DELETE, requestEntity, new ParameterizedTypeReference<Categoria>() {
            });

            return response.getStatusCode();
        } catch (RestClientException e) {
            if (e instanceof HttpStatusCodeException) {
                return HttpStatus.UNPROCESSABLE_ENTITY;
            }
        }

        return HttpStatus.ACCEPTED;
    }

}
