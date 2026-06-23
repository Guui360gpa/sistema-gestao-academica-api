package br.com.sistemagestaoacademica.controller;

import br.com.sistemagestaoacademica.service.curso.CadastrarCurso;
import br.com.sistemagestaoacademica.service.curso.DesativarCurso;
import br.com.sistemagestaoacademica.service.curso.ListarCursosAtivos;
import br.com.sistemagestaoacademica.service.curso.ListarCursosDesativados;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Controller
public class CursoBaseController extends BaseController {

    @Autowired
    private CadastrarCurso cadastrarCurso;
    @Autowired
    private ListarCursosAtivos listarCursosAtivos;
    @Autowired
    private ListarCursosDesativados listarCursosDesativados;
    @Autowired
    private DesativarCurso desativarCurso;

    public void menu() {
        System.out.println("""
                [1] Cadastrar Curso
                [2] Listar cursos ativos
                [3] Listar cursos desativados
                [4] Desativar curso
                """);
        opcao = read.nextInt();
        read.nextLine();

        switch (opcao){
            case 1:
                cadastrarCurso.cadastrar();
                break;
            case 2:
                listarCursosAtivos.listar();
                break;
            case 3:
                listarCursosDesativados.listar();
                break;
            case 4:
                desativarCurso.desativar();
                break;
            default:
                break;
        }
    }
}
