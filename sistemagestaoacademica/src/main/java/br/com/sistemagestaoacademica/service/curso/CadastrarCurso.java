package br.com.sistemagestaoacademica.service.curso;

import br.com.sistemagestaoacademica.models.Curso;
import br.com.sistemagestaoacademica.service.BaseService;

@org.springframework.stereotype.Service
public class CadastrarCurso extends BaseService {
    public void cadastrar(){

        System.out.println("Digite o nome do curso: ");
        var nomeCurso = read.nextLine();

        System.out.printf("Descrição %s:",nomeCurso);
        var descricao = read.nextLine();

        System.out.println("Carga horária (h):");
        var cargaHoraria = read.nextInt();

        cursoRepository.save(new Curso(nomeCurso,descricao,cargaHoraria));
        System.out.println("\nCurso cadastrado com sucesso!");
    }
}
