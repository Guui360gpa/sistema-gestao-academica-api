package br.com.sistemagestaoacademica.principal;

import java.util.Scanner;

public class TurmaMain extends Main{
    @Override
    public void menu() {
        System.out.println("""
                [1] Nova Turma
                [2] Listar Turmas Ativas
                [3] Desativar Turma
                """);
        opcao = read.nextInt();
        read.nextLine();

        switch (opcao){
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;

            default:
                break;
        }
    }
}
