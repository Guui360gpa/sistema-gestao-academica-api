package br.com.sistemagestaoacademica.repository;

import br.com.sistemagestaoacademica.models.Curso;
import br.com.sistemagestaoacademica.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CursoRepository extends JpaRepository<Curso,Long> {

    List<Curso> findByNomeContainingIgnoreCaseAndStatus(String nome, Status status);

    @Query("SELECT c FROM Curso c WHERE c.status = :status")
    List<Curso> findByStatus(@Param("status") Status status);
}
