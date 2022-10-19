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
    public void guardarDinheiro(double valor){
        if(valor <= 0){
            System.out.println("Valor invalido!");
        }else if(valor > saldo){
            System.out.println("Seu saldo é insuficiente!");
        }else{
            try{
                tranferir(valor, this.cliente.getContaPoupanca());
            }catch (NullPointerException e){
                this.cliente.CriarContePoupanca();
                tranferir(valor, this.cliente.getContaPoupanca());
            }
        }
    }
}
