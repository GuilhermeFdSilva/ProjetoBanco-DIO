package br.com.dio.aplicacao;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner ent = new Scanner(System.in);
        int op = 1;
        do{
            Layout.inicio();
            try{
                op = ent.nextInt();
            }catch (InputMismatchException e){
                System.out.println("Argumento invalido!");
                continue;
            }
            if(op == 1){
                System.out.println("Contas");
            }else if (op == 2) {
                System.out.println("Criar Usuario");
            }
        }while(op != 0);
        ent.close();
    }
}
