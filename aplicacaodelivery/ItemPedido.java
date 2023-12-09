/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aplicacaodelivery;

/**
 *
 * @author Carlos Emanuel
 */
class ItemPedido {
    private Produto produto;
    private int quantidade;

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
     public Produto getProduto() {
        return produto;
    }
    
      public int getQuantidade() {
        return quantidade;
    } 
     
}
