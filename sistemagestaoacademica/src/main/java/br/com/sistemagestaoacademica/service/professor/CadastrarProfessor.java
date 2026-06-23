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

        professorRepository.save(professor);
        System.out.println("\nProfessor cadastrado com sucesso!");
    }
}
