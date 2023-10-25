package com.mycompany.uniburguerretaguarda.service;

import com.mycompany.uniburguerretaguarda.dto.LoginDTO;
import com.mycompany.uniburguerretaguarda.gateway.APIGateway;
import javax.swing.JOptionPane;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class LoginService {
    
    private final APIGateway api = new APIGateway();
    
    public void verificaLogin(LoginDTO login) throws Exception {
        HttpStatusCode statusRequisicao = api.verificaLogin(login);
        
        if (!statusRequisicao.equals(HttpStatus.OK)) {
            JOptionPane.showMessageDialog(null, "Login e/ou senha incorretos!");
            throw new Exception();
        }
    }
}
