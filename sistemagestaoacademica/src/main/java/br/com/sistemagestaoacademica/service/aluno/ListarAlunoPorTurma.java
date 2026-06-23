package br.com.sistemagestaoacademica.service.aluno;

import br.com.sistemagestaoacademica.models.Aluno;
import br.com.sistemagestaoacademica.service.BaseService;
import br.com.sistemagestaoacademica.service.turma.ListarTurmasAtivas;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class ListarAlunoPorTurma extends BaseService {

    @Autowired
    private ListarTurmasAtivas listarTurmasAtivas;

    public void listar() {
        listarTurmasAtivas.listar();

        System.out.println("\nDigite o ID da turma desejada: ");
        Long idSelecionado = lerLong();

        List<Aluno> alunosTurma = matriculaRepository.buscarAlunosPorTurma(idSelecionado);

        if (alunosTurma.isEmpty()) {
            System.out.println("Nenhum aluno encontrado nessa turma.");
            return;
        }

        alunosTurma.forEach(a ->
                System.out.printf("RA: %s | Nome: %s | Email: %s\n",
                        a.getRa(), a.getNome(), a.getEmail()));
    }
}
