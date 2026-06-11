package br.com.sistemagestaoacademica.controller;

import br.com.sistemagestaoacademica.repository.TurmaRepository;

public class TurmaController extends Controller {

    private TurmaRepository repository;

    public TurmaController(TurmaRepository turmaRepository){
        this.repository = turmaRepository;
    }

    public TurmaController() {}

    @Override
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
                novaTurma();
                break;
            case 2:
                listarTurmasAtivas();
                break;
            case 3:
                listarTurmasDesativadas();
                break;
            case 4:
                desativarTurmas();
                break;

            default:
                break;
        }
    }

    private void novaTurma() {}

    private void listarTurmasAtivas() {}

    private void listarTurmasDesativadas() {}

    private void desativarTurmas() {}
}
