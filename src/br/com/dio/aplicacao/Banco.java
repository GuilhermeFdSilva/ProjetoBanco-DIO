package br.com.dio.aplicacao;

import br.com.dio.entidades.Conta;

import java.util.ArrayList;
import java.util.List;

public class Banco {
    private static List<Conta> contas = new ArrayList<>();
    public void ativarConta(Conta conta){
        contas.add(conta);
    }
    public static List<Conta> getContas() {
        return contas;
    }
}
