/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aplicacaodelivery;

import java.util.ArrayList;
import java.util.List;


class Estabelecimento {
    private String nome;
    private Endereco endereco;
    private List<Produto> cardapio = new ArrayList<>();
    private List<Pedido> pedidosRealizados = new ArrayList<>();

    public void cadastrar(String nome, Endereco endereco) {
        this.nome = nome;
        this.endereco = endereco;
    }

    public void cadastrarCardapio(Produto produto) {
        cardapio.add(produto);
    }

    public List<Produto> getCardapio() {
        return cardapio;
    }

    public double gerarTotalVendas() {
        double totalVendas = 0.0;
        // Itera sobre todos os pedidos
        for (Pedido pedido : pedidosRealizados) {
            totalVendas += pedido.getTotal();
        }
        return totalVendas;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void adicionarPedido(Pedido pedido) {
        pedidosRealizados.add(pedido);
    }

    public List<Pedido> getPedidosRealizados() {
        return pedidosRealizados;
    }
    
      public boolean haPedidosRealizados() {
        return !pedidosRealizados.isEmpty();
    }
    
}
