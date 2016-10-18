/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.dom4j.DocumentException;

/**
 *
 * @author sala308b
 */
public class Login {
    
    public Boolean autenticar(String login, String senha) throws DocumentException{
        
        boolean retorno = false;
        
        if ( login.equals("") || senha.equals("")){
            return false;
        }
        
        Conexao conexao = null;
        
        try {
             
            conexao = new Conexao();
            
            StringBuilder sb  = new StringBuilder();
            sb.append(" select * from usuarios ");
            sb.append(" where login = ? and senha = ? ");
            sb.append(" and ativo = true ");
            
            String sql = sb.toString();
            
            PreparedStatement stmt = conexao.getCon()
                    .prepareStatement(sql);
            
            stmt.setString(1, login);
            stmt.setString(2, senha);
            
            ResultSet rs =  stmt.executeQuery();
            rs.first();
            
            retorno = rs.getString("login").equals(login)  
                    && rs.getString("senha").equals(senha); 
            
            return retorno;
            
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conexao != null )
               conexao.Fechar();
            conexao = null;
        }
        
        return retorno;
    }
}
