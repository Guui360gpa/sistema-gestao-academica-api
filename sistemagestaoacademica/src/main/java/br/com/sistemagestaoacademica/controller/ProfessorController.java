package br.com.sistemagestaoacademica.controller;

import br.com.sistemagestaoacademica.models.Especialidade;
import br.com.sistemagestaoacademica.models.Professor;
import br.com.sistemagestaoacademica.repository.ProfessorRepository;

import java.util.List;

public class ProfessorController extends Controller {

    private ProfessorRepository repository;

    public ProfessorController(ProfessorRepository professorRepository){
        this.repository = professorRepository;
    }

    public ProfessorController() {}

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
                cadastrarProfessor();
                break;
            case 2:
                acossiarProfessorEmTurma();
                break;
            case 3:
                listarProfessores();
                break;
            case 4:
                desassociarProfessorEmTurma();
                break;

            default:
                break;
        }
    }

    private void cadastrarProfessor() {
        System.out.println("Digite o nome completo do professor: ");
        var nomeProfessor = read.nextLine();

        System.out.printf("Qual é a especialidade de %s\n",nomeProfessor);
        List<Especialidade> especialidades = List.of(Especialidade.values());
        especialidades.forEach(e ->
                System.out.printf(" - %s - \n",e.toString()));

        Especialidade especialidadeProfessor = null;

        while (especialidadeProfessor == null){
            var especialidade = read.nextLine();
            try {
                especialidadeProfessor = Especialidade.fromValor(especialidade);
            } catch (IllegalArgumentException e){
                System.out.println("Especialidade inválida! Escolha uma das opções abaixo:\n");
                especialidades.forEach(c ->
                        System.out.printf(" - %s - \n",c.toString()));

            }
        }

        Professor professor = new Professor(nomeProfessor,especialidadeProfessor);

        repository.save(professor);
        System.out.println("\nProfessor cadastrado com sucesso!");
    }

    private void acossiarProfessorEmTurma() {}

    private void listarProfessores() {}

    private void desassociarProfessorEmTurma() {}
}
