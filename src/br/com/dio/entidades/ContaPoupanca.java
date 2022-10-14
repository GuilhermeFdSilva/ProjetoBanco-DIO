package br.com.dio.entidades;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class ContaPoupanca extends Conta{
    private final List<Deposito> depositos = new ArrayList<>();
    public ContaPoupanca(Cliente cliente) {
        super(cliente);
    }
    @Override
    public void depositar(double valor) {
        super.depositar(valor);
        LocalDate data = LocalDate.now();
        depositos.add(new Deposito(data, valor ));
    }
    @Override
    public void saldo() {
        System.out.println("== Saldo Conta Poupança ==");
        super.saldo();
    }
    public void aplicarCorrecao(){
        double correcao = 0;
        for(Deposito deposito: depositos){
            int dias = (int) ChronoUnit.DAYS.between(deposito.getData(), LocalDate.now());
            correcao += deposito.getValor() * (dias * 0.0043);
        }
        depositar(correcao);
    }

}
class Deposito{
    private final LocalDate data;
    private final double valor;
    public Deposito(LocalDate data, double valor) {
        this.data = data;
        this.valor = valor;
    }
    public LocalDate getData() {
        return data;
    }
    public double getValor() {
        return valor;
    }
}