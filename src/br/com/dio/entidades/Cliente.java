package br.com.dio.entidades;

public class Cliente {
    private static int SEQUENCIAL = 1;
    private final String nome;
    private final int ID;
    private ContaCorrente contaCorrente = null;
    private ContaPoupanca contaPoupanca = null;
    public Cliente(String nome) {
        this.nome = nome;
        ID = SEQUENCIAL++;
    }
    public String getNome() {
        return nome;
    }
    public int getID() {
        return ID;
    }
    public ContaCorrente getContaCorrente() {
        return contaCorrente;
    }
    public void setContaCorrente(ContaCorrente contaCorrente) {
        this.contaCorrente = contaCorrente;
    }
    public ContaPoupanca getContaPoupanca() {
        return contaPoupanca;
    }
    public void setContaPoupanca(ContaPoupanca contaPoupanca) {
        this.contaPoupanca = contaPoupanca;
    }
    public void CriarConteCorrente(){
        if(contaCorrente == null){
            setContaCorrente(new ContaCorrente(this));
        }
    }
    public void CriarContePoupanca(){
        if(contaPoupanca == null){
            setContaPoupanca(new ContaPoupanca(this));
        }
    }
}