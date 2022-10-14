package br.com.dio.entidades;

public class ContaCorrente extends Conta{
    public ContaCorrente(Cliente cliente) {
        super(cliente);
    }
    @Override
    public void saldo() {
        System.out.println("== Saldo Conta Corrente ==");
        super.saldo();
    }
}
