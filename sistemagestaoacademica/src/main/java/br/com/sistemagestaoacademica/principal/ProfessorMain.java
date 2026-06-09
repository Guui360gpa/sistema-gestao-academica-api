package br.com.sistemagestaoacademica.principal;

import java.util.Scanner;

public class ProfessorMain extends Main{

    @Override
    public void menu() {
        System.out.println("""
                
                [1] Cadastrar Professor 
                [2] Associar Professor
                [3] Listar Professores
                [4] Desassociar Professor
                
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
