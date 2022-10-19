package br.com.dio.aplicacao;

import br.com.dio.entidades.Cliente;
import br.com.dio.entidades.Conta;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Banco {
    private static final Map<String, Cliente> clientes = new TreeMap<>();
    private static final Map<String, Conta> contas = new HashMap<>();
    public static Map<String, Cliente> getClientes(){
        return clientes;
    }
    public static Map<String, Conta> getContas() {
        return contas;
    }
   public static void ativarCliente(Cliente cliente){
        clientes.put(cliente.getNome().toLowerCase(), cliente);
   }
    public static void ativarConta(Conta conta){
        contas.put(conta.getCliente().getNome().toLowerCase(), conta);
    }
}
