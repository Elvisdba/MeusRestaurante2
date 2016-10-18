/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Login;
import org.dom4j.DocumentException;

/**
 *
 * @author sala308b
 */
public class LoginController {
    
    Login model;
    
    public Boolean autenticar(String login, String senha ) throws DocumentException{
        
        model = new Login();
        return model.autenticar(login, senha);
    }
    
}
