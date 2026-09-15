package br.com.sistemagestaoacademica.service.curso;

import br.com.sistemagestaoacademica.models.Curso;
import br.com.sistemagestaoacademica.models.Status;
import br.com.sistemagestaoacademica.service.BaseService;

import java.util.List;

@org.springframework.stereotype.Service
public class DesativarCurso extends BaseService {


    private Curso cursoEncontrado = null;
    private List<Curso> cursosEncontrados;

    public void desativar(){


        System.out.println("Qual curso você deseja desativar ?");
        while (cursoEncontrado == null){
            var nomeCurso = read.nextLine();
            cursosEncontrados = cursoRepository.findByNomeContainingIgnoreCaseAndStatus(nomeCurso, Status.ATIVADA);
            if (cursosEncontrados.isEmpty()) {
                System.out.println("\nCurso não encontrado! Tente Novamente.");
            }else {
                listarCursosEncontrados(cursosEncontrados);
                System.out.println("\nDigite o ID do curso desejado: ");
                cursoEncontrado = capturarCursoPorId(lerLong());

                if (cursoEncontrado == null) {
                    System.out.println("\nID inválido! Tente novamente:");
                } else if (cursoEncontrado.getStatus() != Status.ATIVADA) {
                    System.out.println("Erro: o curso \"" + cursoEncontrado.getNome() + "\" já está desativado.");
                    cursoEncontrado = null;
                }
            }
        }

        cursoEncontrado.setStatus(Status.DESATIVADA);
        cursoRepository.save(cursoEncontrado);
    }

    private void listarCursosEncontrados(List<Curso> cursosEncontrados){
        cursosEncontrados.forEach(c ->
                System.out.printf("%s | %s | %s h\n",c.getId(),c.getNome(),c.getCargaHoraria()));
    }

    private Curso capturarCursoPorId(Long id){
        return  cursosEncontrados.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
