/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

/**
 *
 * @author sala308b
 */
public class Comanda {
    private int codigo;
    private List<ItemComanda> itensComanda;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public List<ItemComanda> getItensComanda() {
        return itensComanda;
    }

    public void setItensComanda(List<ItemComanda> itensComanda) {
        this.itensComanda = itensComanda;
    }
    
    
}
