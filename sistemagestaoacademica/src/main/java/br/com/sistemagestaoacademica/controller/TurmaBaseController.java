package br.com.sistemagestaoacademica.controller;

import br.com.sistemagestaoacademica.service.turma.DesativarTurma;
import br.com.sistemagestaoacademica.service.turma.ListarTurmasAtivas;
import br.com.sistemagestaoacademica.service.turma.ListarTurmasDesativadas;
import br.com.sistemagestaoacademica.service.turma.NovaTurma;
import org.springframework.beans.factory.annotation.Autowired;


@org.springframework.stereotype.Controller
public class TurmaBaseController extends BaseController {


    @Autowired
    private NovaTurma novaTurma;
    @Autowired
    private ListarTurmasAtivas listarTurmasAtivas;
    @Autowired
    private ListarTurmasDesativadas listarTurmasDesativadas;
    @Autowired
    private DesativarTurma desativarTurma;

    public void menu() {
        System.out.println("""
                [1] Nova Turma
                [2] Listar Turmas Ativas
                [3] Listar Turmas Desativadas
                [4] Desativar Turma
                """);
        opcao = read.nextInt();
        read.nextLine();

        switch (opcao){
            case 1:
                novaTurma.nova();
                break;
            case 2:
                listarTurmasAtivas.listar();
                break;
            case 3:
                listarTurmasDesativadas.listar();
                break;
            case 4:
                desativarTurma.desativar();
                break;

            default:
                break;
        }
    }
}
