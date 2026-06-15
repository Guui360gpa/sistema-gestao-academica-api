package br.com.sistemagestaoacademica.repository;

import br.com.sistemagestaoacademica.models.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CursoRepository extends JpaRepository<Curso,Long> {

    List<Curso> findByNomeContainingIgnoreCase(String nome);
}
