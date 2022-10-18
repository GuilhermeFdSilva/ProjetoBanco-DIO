package br.com.dio.aplicacao;

import br.com.dio.entidades.Conta;

public class Layout {
    public static void inicio(){
        System.out.println("\t\t\t\t=== DIO Bank ===");
        System.out.println("[1]Acessar\t\t[2]Cadastrar-se\t\t[0]Sair");
    }
    public static void acessarConta(){
        System.out.println("\t\t=== Bem-vindo de volta ===");
        System.out.println("Para começar, insira seu NOME de usuario:");
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
}
