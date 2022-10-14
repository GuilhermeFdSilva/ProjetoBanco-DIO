package br.com.dio.entidades;

public abstract class Conta implements IConta{
    private static final int AGENCIA_PADRAO = 1;
    private static int SEQUENCIAL = 1;
    protected final int agencia;
    protected final int conta;
    protected double saldo = 0;
    protected Cliente cliente;
    public Conta(Cliente cliente) {
        agencia = AGENCIA_PADRAO;
        conta = SEQUENCIAL++;
        this.cliente = cliente;
    }
    @Override
    public void depositar(double valor) {
        saldo += valor;
    }
    @Override
    public void sacar(double valor) {
        if(valor <= 0){
            System.out.println("Valor invalido!");
        }else if(valor > saldo){
            System.out.println("Seu saldo é insuficiente!");
        }else{
            saldo -= valor;
        }
    }
    @Override
    public void tranferir(double valor, Conta alvo) {
        if(valor <= 0){
            System.out.println("Valor invalido!");
        }else if(valor > saldo){
            System.out.println("Seu saldo é insuficiente!");
        }else{
            this.saldo -= valor;
            alvo.saldo += valor;
        }
    }
    public void saldo() {
        System.out.printf("R$%.2f\n", saldo);
    }
    public Cliente getCliente() {
        return cliente;
    }
}