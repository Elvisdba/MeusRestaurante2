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
public class Estoque {
    private int codigo;
    private Produto produto;
    private int qtdeMinima;
    private int qtdeMaxima;
    private int qtdeAtual;

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

    public int getQtdeMinima() {
        return qtdeMinima;
    }

    public void setQtdeMinima(int qtdeMinima) {
        this.qtdeMinima = qtdeMinima;
    }

    public int getQtdeMaxima() {
        return qtdeMaxima;
    }

    public void setQtdeMaxima(int qtdeMaxima) {
        this.qtdeMaxima = qtdeMaxima;
    }

    public int getQtdeAtual() {
        return qtdeAtual;
    }

    public void setQtdeAtual(int qtdeAtual) {
        this.qtdeAtual = qtdeAtual;
    }
    
    
}
