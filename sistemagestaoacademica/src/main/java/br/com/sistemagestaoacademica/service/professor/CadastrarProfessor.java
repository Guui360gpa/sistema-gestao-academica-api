package br.com.sistemagestaoacademica.service.professor;

import br.com.sistemagestaoacademica.models.Especialidade;
import br.com.sistemagestaoacademica.models.Professor;
import br.com.sistemagestaoacademica.service.BaseService;
import java.util.List;

@org.springframework.stereotype.Service
public class CadastrarProfessor extends BaseService {
    public void cadastrar() {
        System.out.println("Digite o nome completo do professor: ");
        var nomeProfessor = read.nextLine();

        System.out.printf("Qual é a especialidade de %s\n",nomeProfessor);
        listarEspecialidades(List.of(Especialidade.values()));


        Especialidade especialidadeProfessor = null;

        while (especialidadeProfessor == null){
            var especialidade = read.nextLine();
            try {
                especialidadeProfessor = Especialidade.fromValor(especialidade);
            } catch (IllegalArgumentException e){
                System.out.println("Especialidade inválida! Escolha uma das opções abaixo:\n");
                listarEspecialidades(List.of(Especialidade.values()));

            }
        }

        Professor professor = new Professor(nomeProfessor,especialidadeProfessor);

        salvarProfessorNoBanco(professor);
        System.out.println("\nProfessor cadastrado com sucesso!");
    }

    private Professor salvarProfessorNoBanco(Professor professor){
        return professorRepository.save(professor);
    }

    private void listarEspecialidades(List<Especialidade> especialidades){
        especialidades.forEach(e ->
                System.out.printf(" - %s - \n",e.toString()));
    }
}
