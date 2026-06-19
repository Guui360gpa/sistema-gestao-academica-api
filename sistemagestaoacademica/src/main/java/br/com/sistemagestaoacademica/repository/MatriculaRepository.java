package br.com.sistemagestaoacademica.repository;

import br.com.sistemagestaoacademica.models.Aluno;
import br.com.sistemagestaoacademica.models.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MatriculaRepository extends JpaRepository<Matricula,Long> {

    @Query("SELECT m.aluno FROM Matricula m WHERE m.turma.id = :idTurma")
    List<Aluno> buscarAlunosPorTurma(@Param("idTurma") Long idTurma);

    boolean existsByAlunoIdAndTurmaId(Long alunoId, Long turmaId);
}
