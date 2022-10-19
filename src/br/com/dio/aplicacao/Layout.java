package br.com.dio.aplicacao;

import br.com.dio.entidades.Conta;

public class Layout {
    public static void inicio(){
        System.out.println("\t\t\t\t=== DIO Bank ===");
        System.out.println("[1]Acessar\t\t[2]Cadastrar-se\t\t[0]Sair");
    }
    public static void acessarCliente(){
        System.out.println("\t\t=== Bem-vindo de volta ===");
        System.out.println("Para começar, insira seu NOME de usuario:");
    }
    public static void clienteNaoEncontrado(){
        System.out.println("Cliente não encontrado");
        System.out.println("[1]Tentar novamente\t[2]Cadastrar-se\t[3]Voltar ao Inicio");
    }
    public static void acessarConta(){
        System.out.println("Qual conta deseja acessar?");
        System.out.println("[1]Conta Corrente\t[2]Conta Poupança\t[3]Criar Conta\t[4]Sair");
    }
    public static void criarConta(){
        System.out.println("Selecione o tipo de conta que deseja:");
        System.out.println("[1]Conta Corrente\t[2]Conta Poupança");
    }
    public static void cadastro(){
        System.out.println("\t\t\t=== Bem-Vindo a DIO ===");
        System.out.println("\tPara realizar seu cadastro é bem simples");
        System.out.println("Basta nos dizer seu nome ou digite \"Cancelar\" para sair: ");
    }
    public static void infoConta(Conta conta ){
        System.out.printf("Agencia: 000%d\nConta: %d\n", conta.getAgencia(), conta.getConta());
        conta.saldo();
    }
    public static void operacoesDaContaCorrente(){
        System.out.println("[1]Sacar\t[2]Depositar\t[3]Transferir\t[4]Guardar Dinheiro\t[0]Sair");
    }
    public static void operacoesDaContaPoupanca(){
        System.out.println("[1]Sacar\t[2]Depositar\t[3]Transferir\t[4]Resgatar Dinheiro\t[0]Sair");
    }
}
