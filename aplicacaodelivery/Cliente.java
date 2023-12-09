/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aplicacaodelivery;

/**
 *
 * @author Carlos Emanuel
 */
class Cliente {
    private String nome;
    private String telefone;
    private Endereco endereco;

    public void cadastrar(String nome, String telefone, Endereco endereco) {
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
    }
    
     public String getNome() {
        return nome;
    }
    
}
