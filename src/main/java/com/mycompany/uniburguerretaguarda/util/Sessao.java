package com.mycompany.uniburguerretaguarda.util;

import com.mycompany.uniburguerretaguarda.dto.response.LoginResponse;

public class Sessao {
    
    public static int idUsuario;
    public static String login;
    public static String nome;
    
    public static void setSessao(LoginResponse loginResp) {
        idUsuario = loginResp.getId();
        login = loginResp.getLogin();
        nome = loginResp.getNome();
    }
}
