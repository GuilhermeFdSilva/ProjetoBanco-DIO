package br.com.dio.aplicacao;

import br.com.dio.entidades.Cliente;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        do{
            int op = iniciar();
            if(op == 1){
                Cliente cliente = acessarCliente();
                if(cliente == null){
                    continue;
                }
                System.out.println(cliente.getID());
            }else if(op == 2){
                try{
                    Cliente cliente = acessarCliente(cadastrarCliente());
                    System.out.printf("Seja Bem-vindo %s seu ID é: %d", cliente.getNome(), cliente.getID());
                }catch(NullPointerException ignored){
                }
            }else{
                break;
            }
        }while(true);
    }
    public static int iniciar(){
        int op = 10;
        do{
            String opString;
            Layout.inicio();
            opString = new Scanner(System.in).nextLine();
            try{
                op = Integer.parseInt(opString);
            }catch (NumberFormatException e){
                System.out.println("Argumento invalido!");
                continue;
            }
            if(op > 2 || op < 0){
                System.out.println("Argumento Invalido!");
            }
        }while(op > 2 || op < 0);
        return op;
    }
    public static Cliente acessarCliente(){
        int op;
        String nome;
        Layout.acessarConta();
        nome = new Scanner(System.in).nextLine();
        Cliente cliente = Banco.getClientes().get(nome);
        if(cliente == null){
            System.out.println("Cliente não encontrado");
            System.out.println("[1]Tentar novamente\t[2]Cadastrar-se\t[3]Voltar ao Inicio");
            try{
                op = Integer.parseInt(String.valueOf(new Scanner(System.in).nextLine()));
                if(op == 1){
                    acessarCliente();
                } else if(op == 2) {
                    try{
                        return acessarCliente(cadastrarCliente());
                    }catch (NullPointerException ignored){
                        return null;
                    }
                }else if(op == 3){
                    return null;
                }else{
                    throw new NumberFormatException();
                }
            }catch (NumberFormatException e){
                System.out.println("Argumento Invalido");
                return null;
            }
        }
        return cliente;
    }
    public static Cliente acessarCliente(String nome){
        return Banco.getClientes().get(nome);
    }
    private static String cadastrarCliente() {
        Layout.cadastro();
        String nome = new Scanner(System.in).nextLine();
        if(!nome.equalsIgnoreCase("cancelar")){
            Cliente cliente = new Cliente(nome);
            Banco.ativarCliente(cliente);
            return cliente.getNome();
        }
        return null;
    }
}
