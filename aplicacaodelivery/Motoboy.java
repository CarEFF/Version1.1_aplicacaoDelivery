/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aplicacaodelivery;

/**
 *
 * @author Carlos Emanuel
 */
class Motoboy {
       public void realizarEntrega(Estabelecimento estabelecimento, Pedido pedido) {
        System.out.println("Entregando pedido para: " + pedido.getCliente().getNome());
        System.out.println("Itens do pedido:");
        for (ItemPedido item : pedido.getItens()) {
            System.out.println("- Produto: " + item.getProduto().getNome() +
                    ", Quantidade: " + item.getQuantidade());
        }
        System.out.println("Total do pedido: " + pedido.getTotal());
        System.out.println("Entrega realizada com sucesso!");

        pedido.setEntregue(true);
        estabelecimento.adicionarPedido(pedido);        
    }
}