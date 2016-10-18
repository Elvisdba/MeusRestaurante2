/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sala308b
 */
public interface IDao {
    
    public boolean incluir(Object o);
    public boolean excluir(Object o);
    public boolean update(Object o);
    public ArrayList<Object> listar(); 
    public Object exibir(Object o);
}
