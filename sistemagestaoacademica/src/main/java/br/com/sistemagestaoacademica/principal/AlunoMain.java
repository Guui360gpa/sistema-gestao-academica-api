package br.com.sistemagestaoacademica.principal;

import java.util.Scanner;

public class AlunoMain extends Main{

    @Override
    public void menu(){
        System.out.println("""

                [1] Cadastrar Aluno
                [2] Matricular Aluno
                [3] Listar Alunos Por Turma
                [4] Desmatricular Aluno
                
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
            case 4:
                break;

            default:
                break;

        }
    }
}
