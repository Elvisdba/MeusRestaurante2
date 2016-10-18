/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author sala308b
 */
public class ItemComanda {
    private int codigo;
    private Produto produto;
    private int qtde;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQtde() {
        return qtde;
    }

    public void setQtde(int qtde) {
        this.qtde = qtde;
    }

    public ItemComanda(int codigo, Produto produto, int qtde) {
        this.codigo = codigo;
        this.produto = produto;
        this.qtde = qtde;
    }
    
    
}
