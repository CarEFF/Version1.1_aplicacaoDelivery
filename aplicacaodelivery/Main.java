/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.aplicacaodelivery;

/**
 *
 * @author Carlos Emanuel
 */

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

// ...

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Estabelecimento estabelecimento = new Estabelecimento();
 	Motoboy motoboy = new Motoboy();  // Adicionei a instância do Motoboy
        List<Pedido> pedidosPendentes = new ArrayList<>();  // Adicionei a lista de pedidos pendentes

        do {
            System.out.println("Você é a) Estabelecimento ou b) Cliente? c)Relatorio");
            String opcao = scanner.nextLine();
        
        try {    
            if (opcao.equalsIgnoreCase("a")) {
                System.out.println("Digite o nome do estabelecimento:");
                String nome = scanner.nextLine();
                System.out.println("Digite o endereço do estabelecimento:");
                String enderecoStr = scanner.nextLine();
                Endereco endereco = new Endereco();
                endereco.setRua(enderecoStr);
                estabelecimento.cadastrar(nome, endereco);

                System.out.println("Deseja cadastrar o cardápio? (s/n)");
                String cadastrarCardapio = scanner.nextLine();
                while (cadastrarCardapio.equalsIgnoreCase("s")) {
                    System.out.println("Digite o nome do produto:");
                    String nomeProduto = scanner.nextLine();
                    System.out.println("Digite o preço do produto:");
                    double precoProduto = 0.0;
                    try {
                        precoProduto = scanner.nextDouble();
                    } catch (InputMismatchException e) {
                        System.out.println("Valor inválido. Insira um número para o preço.");
                        scanner.next(); // Limpa o buffer do scanner
                        continue;
                    }
                    scanner.nextLine(); // Limpa o buffer do scanner
                    Produto produto = new Produto();
                    produto.setNome(nomeProduto);
                    produto.setPreco(precoProduto);
                    estabelecimento.cadastrarCardapio(produto);
                    System.out.println("Deseja cadastrar outro produto no cardápio? (s/n)");
                    cadastrarCardapio = scanner.nextLine();
                    
                    
                }
                // Exibe os detalhes dos pedidos realizados
                for (Pedido pedido : estabelecimento.getPedidosRealizados()) {
                    System.out.println("Detalhes do pedido para " + estabelecimento.getNome() + ":");
                    System.out.println("Cliente: " + pedido.getCliente().getNome());

                    // Exibe os itens do pedido
                    System.out.println("Itens do pedido:");
                    for (ItemPedido item : pedido.getItens()) {
                        System.out.println("- Produto: " + item.getProduto().getNome() +
                                ", Quantidade: " + item.getQuantidade() +
                                ", Subtotal: " + item.getProduto().getPreco() * item.getQuantidade());
                    }

                    System.out.println("Total do pedido: " + pedido.getTotal());
                }

                
            } else if (opcao.equalsIgnoreCase("b")) {
                Cliente cliente = new Cliente();
                System.out.println("Digite o nome do cliente:");
                String nome = scanner.nextLine();
                System.out.println("Digite o telefone do cliente:");
                String telefone = scanner.nextLine();
                System.out.println("Digite o endereço do cliente:");
                String enderecoStr = scanner.nextLine();
                Endereco endereco = new Endereco();
                endereco.setRua(enderecoStr);
                cliente.cadastrar(nome, telefone, endereco);

                System.out.println("Deseja realizar um pedido? (s/n)");
                String realizarPedido = scanner.nextLine();
                if (realizarPedido.equalsIgnoreCase("s")) {
                    Pedido pedido = new Pedido();
                    pedido.setCliente(cliente);
                    String adicionarItem = "s";
                    while (adicionarItem.equalsIgnoreCase("s")) {
                        System.out.println("Digite o nome do produto que deseja adicionar ao pedido:");
                        String nomeProduto = scanner.nextLine();
                        for (Produto produto : estabelecimento.getCardapio()) {
                            if (produto.getNome().equalsIgnoreCase(nomeProduto)) {
                                ItemPedido item = new ItemPedido();
                                item.setProduto(produto);
                                    // Solicitação da quantidade com tratamento para valores negativos
                                    int quantidade = 0;
                                    while (quantidade <= 0) {
                                        System.out.println("Digite a quantidade desse produto (deve ser maior que zero):");
                                        try {
                                            quantidade = scanner.nextInt();
                                            if (quantidade <= 0) {
                                                System.out.println("Quantidade inválida. Insira um valor maior que zero.");
                                            }
                                        } catch (InputMismatchException e) {
                                            System.out.println("Entrada inválida. Insira um valor numérico inteiro maior que zero.");
                                            scanner.next(); // Limpa o buffer do scanner
                                        }
                                    }

                                    item.setQuantidade(quantidade);
                                    pedido.getItens().add(item);
                                    pedido.setTotal(pedido.getTotal() + produto.getPreco() * quantidade);
                                }
                            }
                        
                        System.out.println("Deseja adicionar outro item ao pedido? (s/n)");
                        adicionarItem = scanner.next();
                        scanner.nextLine();
                    }

                    System.out.println("Deseja realizar um pagamento? (s/n)");
                    String realizarPagamento = scanner.next();
                    if (realizarPagamento.equalsIgnoreCase("s")) {
                        System.out.println("Escolha a forma de pagamento: a) Débito ou b) Crédito");
                        String formaPagamento = scanner.next();
                        if (formaPagamento.equalsIgnoreCase("b")) {
                            pedido.setTotal(pedido.getTotal() + 1); // Adicional de 1 real para pagamento no crédito
                        }
                        System.out.println("Pagamento realizado. Valor total: " + pedido.getTotal());
			
			//Método realizarEntrega do motoboy
                       motoboy.realizarEntrega(estabelecimento, pedido);
                    }
                }      
            } else if (opcao.equalsIgnoreCase("c")) {
                // Verifica se já foi cadastrado um estabelecimento
                if (estabelecimento.getNome() == null) {
                    System.out.println("Nenhum estabelecimento cadastrado. Cadastre um estabelecimento antes.");
            } else {
                // Adicione os pedidos pendentes à lista antes de exibir os detalhes
                for (Pedido pedido : estabelecimento.getPedidosRealizados()) {
                    if (!pedidosPendentes.contains(pedido)) {
                        pedidosPendentes.add(pedido);
                    }
                }
                // Exibe os detalhes dos pedidos pendentes
                for (Pedido pedido : pedidosPendentes) {
                    System.out.println("Relatorio do dia para empresa " + estabelecimento.getNome() + ":");
                }
                // Calcula e exibe o total de vendas
                double totalVendas = estabelecimento.gerarTotalVendas();
                System.out.println("O total de vendas do dia é: " + totalVendas);
                break;  // Sai do loop ao escolher "c"
            }
            
            }else {
                System.out.println("Opção inválida. Por favor, escolha 'a', 'b' ou 'c'.");
                 // Volta ao início do loop
                    }
                    // Limpa o buffer do scanner antes de ler a opção do usuário
                    String respostaUsuario = scanner.nextLine();
                    // Pergunta ao usuário se deseja realizar mais alguma ação
                    System.out.println("Deseja voltar ao inicio? (s/n)");
                    
                  } catch (InputMismatchException e) {
                    System.out.println("Entrada inválida. Por favor, insira um valor válido.");
                    scanner.next(); // Limpa o buffer do scanner
                  } catch (NumberFormatException e) {
                    System.out.println("Formato numérico inválido. Por favor, insira um número válido.");
                  } catch (IndexOutOfBoundsException e) {
                    System.out.println("Índice fora dos limites. Por favor, verifique a entrada.");
                  } catch (NullPointerException e) {
                    System.out.println("Referência nula. Por favor, verifique a entrada.");
                }    
                 
                } while (scanner.nextLine().equalsIgnoreCase("s"));

                System.out.println("Programa encerrado. Obrigado!");
                scanner.close();
    }
}
