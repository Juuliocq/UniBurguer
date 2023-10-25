package com.mycompany.uniburguerretaguarda.service;

import com.mycompany.uniburguerretaguarda.gateway.APIGateway;
import com.mycompany.uniburguerretaguarda.model.Categoria;
import java.util.List;
import javax.swing.JOptionPane;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class CategoriaService {
    
    private final APIGateway api = new APIGateway();
    
    public List<Categoria> getCategorias() {
        return api.getCategorias();
    }
    
    public void salvar(Categoria categoria) throws Exception {
        validaCategoria(categoria);
        
        if (categoria.getId() > 0) {
            alteraCategoria(categoria);
        } else {
            criaCategoria(categoria);
        }
    }
    
    private void criaCategoria(Categoria categoria) throws Exception {
        HttpStatusCode statusRequisicao = api.postCategoria(categoria);
        
        if (statusRequisicao.equals(HttpStatus.ACCEPTED)) {
            JOptionPane.showMessageDialog(null, "Criado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao criar a categoria!");
            throw new Exception();
        }
    }
    
    private void alteraCategoria(Categoria categoria) throws Exception {
        HttpStatusCode statusRequisicao = api.putCategoria(categoria);
        
        if (statusRequisicao.equals(HttpStatus.ACCEPTED)) {
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } else if (statusRequisicao.equals(HttpStatus.NO_CONTENT)) {
            JOptionPane.showMessageDialog(null, "Categoria não encontrada!");
            throw new Exception();
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao salvar a categoria!");
            throw new Exception();
        }
    }
    
    private void validaCategoria(Categoria categoria) throws Exception {        
        if (categoria.getDescricao().isBlank()) {
            JOptionPane.showMessageDialog(null, "O nome da categoria não pode ser vazio!");
            throw new Exception();
        }
        
        boolean existeNomeIgual = getCategorias().stream().anyMatch(i -> 
                i.getDescricao().equalsIgnoreCase(categoria.getDescricao())
                && i.getId() != categoria.getId());

        if (existeNomeIgual) {
            JOptionPane.showMessageDialog(null, "Já existe uma categoria cadastrada com esse nome!");
            throw new Exception();
        }
    }
    
    public void excluirCategoria(Categoria categoria) throws Exception {
        HttpStatusCode statusRequisicao = api.deleteCategoria(categoria);

        if (statusRequisicao.equals(HttpStatus.ACCEPTED)) {
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
        } else if (statusRequisicao.equals(HttpStatus.NO_CONTENT)) {
            JOptionPane.showMessageDialog(null, "Categoria não encontrada!");
            throw new Exception();
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao excluir a categoria! Certifique-se de que todos os produtos estão excluídos!");
            throw new Exception();
        }
    }
    
}
