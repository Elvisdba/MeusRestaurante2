/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;

/**
 *
 * @author Wanderlei
 */
public class UsuariosController {
    private dao.UsuarioDAO dao = null;
    private dao.Conexao conexao = null;
    
    public UsuariosController(){
        dao = new dao.UsuarioDAO();
    }
    
    public boolean incluir(model.Usuario u){
          return dao.incluir(u);
    }
    
    public boolean excluir(model.Usuario u ){
        return dao.excluir(u);
    }
    public boolean atualizar(model.Usuario u){
        return dao.update(u);
    }
    
    public model.Usuario exibir(model.Usuario u){
        return (model.Usuario) dao.exibir(u);
    }
    
    public ArrayList<Object> listar(){
        return dao.listar();
    }

    public model.Usuario pesquisar(String tipo, String texto) {
        return dao.pesquisar(tipo, texto);        
    }
}
