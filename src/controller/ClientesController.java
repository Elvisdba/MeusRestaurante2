/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ClienteDAO;
import java.util.ArrayList;
import model.Cliente;

/**
 *
 * @author sala308b
 */
public class ClientesController  {
    
    private ClienteDAO dao = null;
    
    public ClientesController(){
        dao = new ClienteDAO();
    }
    
    public ArrayList<Object> listar(){        
        return dao.listar() ;
    }
    
    public boolean incluir(Cliente cli){        
        return dao.incluir(cli);
    }
    public boolean excluir(Cliente cli){
        return dao.excluir(cli);
    }
    public Cliente exibir(Cliente cli){
        return (Cliente) dao.exibir(cli);
    }
    public boolean atualizar(Cliente cli){
        return dao.update(cli);
    }
    
    public Cliente pesquisarPorNome(String nome){
        return dao.pesquisarPorNome(nome);
    }
    public Cliente pesquisarPorCpf(String cpf){
        return dao.pesquisarPorCpf(cpf);
    }
}
