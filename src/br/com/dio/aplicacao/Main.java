package br.com.dio.aplicacao;

import br.com.dio.entidades.Cliente;
import br.com.dio.entidades.Conta;
import br.com.dio.entidades.ContaCorrente;
import br.com.dio.entidades.ContaPoupanca;

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
                if(!verificarID(cliente)){
                    continue;
                }
                exibirContas(cliente);
                Conta conta = null;
                do {
                    if (conta == null) {
                        conta = acessarConta(cliente);
                    }
                    try{
                        if (!operacoesDaConta(conta)){
                            break;
                        }
                    }catch (NullPointerException e){
                        System.out.println("Vamos criar sua conta");
                        criarConta(cliente);
                    }
                } while (true);
            }else if(op == 2){
                try{
                    Cliente cliente = acessarCliente(cadastrarCliente());
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
        nome = new Scanner(System.in).nextLine().toLowerCase();
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
            System.out.printf("Seja Bem-vindo %s seu ID é: %d\n\n", cliente.getNome(), cliente.getID());
            return cliente.getNome();
        }
        return null;
    }
    private static boolean verificarID(Cliente cliente) {
        System.out.println("Digite seu ID de usuario: ");
        String idString = new Scanner(System.in).nextLine();
        try{
            return cliente.getID() == Integer.parseInt(idString);
        }catch (NumberFormatException e){
            System.out.println("Argumento Invalido!");
            return false;
        }
    }
    public static void exibirContas(Cliente cliente){
        Conta contaCorrente = cliente.getContaCorrente();
        Conta contaPoupanca = cliente.getContaPoupanca();
        if(contaCorrente == null){
            System.out.println("Você ainda não possue uma Conta Corrente");
        }else{
            System.out.println(contaCorrente);
        }
        if(contaPoupanca == null){
            System.out.println("Você ainda não possue uma Conta Poupança");
        }else{
            System.out.println(contaPoupanca);
        }
    }
    public static Conta acessarConta(Cliente cliente){
        do{
            System.out.println("Qual conta deseja acessar?");
            System.out.println("[1]Conta Corrente\t[2]Conta Poupança\t[3]Criar Conta");
            String tipoString = new Scanner(System.in).nextLine();
            try{
                int tipo = Integer.parseInt(tipoString);
                if(tipo == 1){
                        return cliente.getContaCorrente();
                } else if(tipo == 2){
                        return cliente.getContaCorrente();
                }else if(tipo == 3){
                    criarConta(cliente);

                }else{
                    System.out.println("Argumento Invalido!");
                }
            }catch (NumberFormatException e){
                System.out.println("Argumento Invalido!");
            }
        }while (true);
    }
    public static void criarConta(Cliente cliente){
        System.out.println("Selecione o tipo de conta que deseja:");
        System.out.println("[1]Conta Corrente\t[2]Conta Poupança");
        String tipoString = new Scanner(System.in).nextLine();
        try{
            int tipo = Integer.parseInt(tipoString);
            if(tipo == 1){
                ContaCorrente conta = cliente.getContaCorrente();
                if(conta == null){
                    conta = new ContaCorrente(cliente);
                    cliente.setContaCorrente(conta);
                }
                Banco.ativarConta(conta);
                Layout.infoConta(conta);
                exibirContas(cliente);
            } else if(tipo == 2){
                ContaPoupanca conta = cliente.getContaPoupanca();
                if(conta == null){
                    conta = new ContaPoupanca(cliente);
                    cliente.setContaPoupanca(conta);
                }
                Banco.ativarConta(conta);
                Layout.infoConta(conta);
                exibirContas(cliente);
            }else{
                System.out.println("Argumento Invalido!");
            }
        }catch (NumberFormatException e){
            System.out.println("Argumento Invalido!");
        }
    }
    public static boolean operacoesDaConta(Conta conta){
        Layout.infoConta(conta);
        System.out.println("[1]Sacar\t[2]Depositar\t[3]Transferir\t[0]Sair");
        String operacaoString = new Scanner(System.in).nextLine();
        try{
            int operacao = Integer.parseInt(operacaoString);
            if(operacao == 1){
                System.out.println("Qual valor deseja sacar?");
                String valorString = new Scanner(System.in).nextLine();
                conta.sacar(Double.parseDouble(valorString.replace(",", ".")));
                return true;
            } else if(operacao == 2){
                System.out.println("Qual valor deseja depositar?");
                String valorString = new Scanner(System.in).nextLine();
                conta.depositar(Double.parseDouble(valorString.replace(",", ".")));
                return true;
            } else if(operacao == 3){
                transferencia(conta);
                return true;
            } else if(operacao == 0){
                return false;
            }else{
                System.out.println("Argumento Invalido!");
                operacoesDaConta(conta);
            }
        }catch (NumberFormatException e){
            System.out.println("Argumento Invalido!");
            return true;
        }
        return false;
    }
    public static void transferencia(Conta conta){
        System.out.println("Qual o nome do destinatario?");
        String alvo = new Scanner(System.in).nextLine();
        Conta contaAlvo = Banco.getContas().get(alvo);
        if(contaAlvo == null){
            System.out.println("Cliente não encontrado");
        }else{
            System.out.println("Qual valor da transferencia?");
            String valorString = new Scanner(System.in).nextLine();
            try{
                conta.tranferir(Double.parseDouble(valorString.replace(",", ".")), contaAlvo);
            }catch (NumberFormatException e){
                System.out.println("Argumento Invalido!");
            }
        }
    }
}