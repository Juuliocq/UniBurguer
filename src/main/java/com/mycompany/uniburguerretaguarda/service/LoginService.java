package com.mycompany.uniburguerretaguarda.service;

import com.mycompany.uniburguerretaguarda.dto.LoginDTO;
import com.mycompany.uniburguerretaguarda.dto.request.LoginRequest;
import com.mycompany.uniburguerretaguarda.dto.response.LoginResponse;
import com.mycompany.uniburguerretaguarda.gateway.APIGateway;
import com.mycompany.uniburguerretaguarda.util.Sessao;
import javax.swing.JOptionPane;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class LoginService {
    
    private final APIGateway api = new APIGateway();
    
    public void verificaLogin(LoginDTO login) throws Exception {
        
        if (login.getLogin().isBlank()
                || login.getSenha().isBlank()) {
            JOptionPane.showMessageDialog(null, "Usuário e/ou senha vazio!");
        }
        
        ResponseEntity<LoginResponse> requisicao = api.verificaLogin(login);
        
        if (!requisicao.getStatusCode().equals(HttpStatus.OK)) {
            JOptionPane.showMessageDialog(null, "Login e/ou senha incorretos!");
            throw new Exception();
        }
        
        Sessao.setSessao(requisicao.getBody());
    }
    
    public void putLogin(LoginRequest login, int pIdLogin) throws Exception {
        
        if (login.getLogin().isBlank()
                || login.getNome().isBlank()) {
            JOptionPane.showMessageDialog(null, "Usuário e/ou nome vazio!");
        }
        
        HttpStatusCode requisicao = api.putLogin(login, pIdLogin);
        
        if (!requisicao.equals(HttpStatus.ACCEPTED)) {
            JOptionPane.showMessageDialog(null, "Não foi possível alterar o login!");
            throw new Exception();
        }
        
        JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
    }
}
