package br.com.sistemagestaoacademica.controller;

import br.com.sistemagestaoacademica.models.Curso;
import br.com.sistemagestaoacademica.repository.CursoRepository;

public class CursoController extends Controller{

    private CursoRepository repository;

    public CursoController(CursoRepository cursoRepository) {
        this.repository = cursoRepository;
    }

    public CursoController() {}

    @Override
    public void menu() {
        System.out.println("""
                [1] Cadastrar Curso
                [2] Ativar Curso
                [3] Desativar Curso
                """);
        opcao = read.nextInt();
        read.nextLine();

        switch (opcao){
            case 1:
                cadastrarTurma();
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                break;
        }
    }

    private void cadastrarTurma(){

        System.out.println("Digite o nome do curso: ");
        var nomeCurso = read.nextLine();

        System.out.printf("Descrição %s:",nomeCurso);
        var descricao = read.nextLine();

        System.out.println("Carga horária (h):");
        var cargaHoraria = read.nextInt();

        repository.save(new Curso(nomeCurso,descricao,cargaHoraria));
        System.out.println("\nCurso cadastrado com sucesso!");
    }
}
