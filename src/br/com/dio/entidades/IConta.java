package br.com.dio.entidades;

public interface IConta {
    void depositar(double valor);
    void sacar(double valor);
    void tranferir(double valor, Conta alvo);
}
