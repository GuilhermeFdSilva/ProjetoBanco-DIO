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
                acessarConta(cliente);
            }else if(op == 2){
                try{
                    acessarCliente(cadastrarCliente());
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
        Layout.acessarCliente();
        nome = new Scanner(System.in).nextLine().toLowerCase();
        Cliente cliente = Banco.getClientes().get(nome);
        if(cliente == null){
            Layout.clienteNaoEncontrado();
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
    public static void acessarConta(Cliente cliente){
        do{
            Layout.acessarConta();
            String tipoString = new Scanner(System.in).nextLine();
            try{
                int tipo = Integer.parseInt(tipoString);
                if(tipo == 1){
                    do{
                        ContaCorrente conta = cliente.getContaCorrente();
                        try{
                            if(!operacoesDaContaCorrente(conta)){
                                break;
                            }
                        }catch (NullPointerException e){
                            System.out.println("Vamos criar sua conta");
                            criarConta(cliente);
                        }
                    }while(true);
                } else if(tipo == 2){
                    do {
                        ContaPoupanca conta = cliente.getContaPoupanca();
                        try{
                            conta.aplicarCorrecao();
                            if(!operacoesDaContaPoupanca(conta)){
                                break;
                            }
                        }catch (NullPointerException e){
                            System.out.println("Vamos criar sua conta");
                            criarConta(cliente);
                        }
                    }while(true);
                }else if(tipo == 3){
                    criarConta(cliente);
                }else if(tipo == 4){
                    break;
                }else{
                    System.out.println("Argumento Invalido!");
                }
            }catch (NumberFormatException e){
                System.out.println("Argumento Invalido!");
            }
        }while (true);
    }
    public static void criarConta(Cliente cliente){
        Layout.criarConta();
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
    public static boolean operacoesDaContaCorrente(ContaCorrente conta){
        Layout.infoConta(conta);
        Layout.operacoesDaContaCorrente();
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
            } else if(operacao == 4){
                guardarDinheiro(conta);
            }else if(operacao == 0) {
                return false;
            }else{
                System.out.println("Argumento Invalido!");
                operacoesDaContaCorrente(conta);
            }
        }catch (NumberFormatException e){
            System.out.println("Argumento Invalido!");
            return true;
        }
        return false;
    }
    public static boolean operacoesDaContaPoupanca(ContaPoupanca conta){
        Layout.infoConta(conta);
        Layout.operacoesDaContaPoupanca();
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
            } else if(operacao == 4){
                resgatarDinheiro(conta);
            }else if(operacao == 0) {
                return false;
            }else{
                System.out.println("Argumento Invalido!");
                operacoesDaContaPoupanca(conta);
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
    public static void guardarDinheiro(ContaCorrente conta){
        System.out.println("Qual valor deseja guardar?");
        String valorString = new Scanner(System.in).nextLine();
        try{
            conta.guardarDinheiro(Double.parseDouble(valorString.replace(",", ".")));
        }catch (NumberFormatException e){
            System.out.println("Argumento Invalido!");
        }
    }
    public static void resgatarDinheiro(ContaPoupanca conta){
        System.out.println("Qual valor deseja resgatar?");
        String valorString = new Scanner(System.in).nextLine();
        try{
            conta.resgatarDinheiro(Double.parseDouble(valorString.replace(",", ".")));
        }catch (NumberFormatException e){
            System.out.println("Argumento Invalido!");
        }
    }
}