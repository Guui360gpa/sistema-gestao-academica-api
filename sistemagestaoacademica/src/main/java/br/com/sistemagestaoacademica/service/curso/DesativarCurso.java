package br.com.sistemagestaoacademica.service.curso;

import br.com.sistemagestaoacademica.models.Curso;
import br.com.sistemagestaoacademica.models.Status;
import br.com.sistemagestaoacademica.service.BaseService;

import java.util.List;

@org.springframework.stereotype.Service
public class DesativarCurso extends BaseService {
    public void desativar(){
        Curso cursoEncontrado = null;
        List<Curso> cursosEncontrados;

        System.out.println("Qual curso você deseja desativar ?");
        while (cursoEncontrado == null){
            var nomeCurso = read.nextLine();
            cursosEncontrados = cursoRepository.findByNomeContainingIgnoreCaseAndStatus(nomeCurso, Status.ATIVADA);
            if (cursosEncontrados.isEmpty()) {
                System.out.println("\nCurso não encontrado! Tente Novamente.");
            }else {
                cursosEncontrados.forEach(c ->
                        System.out.printf("%s | %s | %s h\n",c.getId(),c.getNome(),c.getCargaHoraria()));
                System.out.println("\nDigite o ID do curso desejado: ");
                Long idSelecionado = lerLong();
                cursoEncontrado = cursosEncontrados.stream()
                        .filter(p -> p.getId().equals(idSelecionado))
                        .findFirst()
                        .orElse(null);

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
}
