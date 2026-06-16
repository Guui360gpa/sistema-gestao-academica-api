package br.com.sistemagestaoacademica.repository;

import br.com.sistemagestaoacademica.models.Curso;
import br.com.sistemagestaoacademica.models.Professor;
import br.com.sistemagestaoacademica.models.StatusTurma;
import br.com.sistemagestaoacademica.models.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TurmaRepository extends JpaRepository<Turma,Long> {

    List<Turma> findByStatusTurma(StatusTurma statusTurma);

    @Query("SELECT t FROM Turma t WHERE LOWER(t.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<Turma> buscarTurmaPorNome(@Param("nome") String nome);
}
