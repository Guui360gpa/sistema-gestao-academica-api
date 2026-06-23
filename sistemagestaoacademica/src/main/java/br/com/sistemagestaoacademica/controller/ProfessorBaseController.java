package br.com.sistemagestaoacademica.controller;


import br.com.sistemagestaoacademica.service.professor.CadastrarProfessor;
import br.com.sistemagestaoacademica.service.professor.ListarProfessores;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Controller
public class ProfessorBaseController extends BaseController {
    @Autowired
    private CadastrarProfessor cadastrarProfessor;
    @Autowired
    private ListarProfessores listarProfessores;

    public void menu() {
        System.out.println("""
                
                [1] Cadastrar Professor 
                [2] Listar Professores
                
                """);
        opcao = read.nextInt();
        read.nextLine();

        switch (opcao){
            case 1:
                cadastrarProfessor.cadastrar();
                break;
            case 2:
                listarProfessores.listar();
                break;

            default:
                break;
        }
    }
}
