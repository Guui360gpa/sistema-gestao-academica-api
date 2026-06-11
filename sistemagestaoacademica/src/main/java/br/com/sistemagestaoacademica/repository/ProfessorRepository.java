package br.com.sistemagestaoacademica.repository;

import br.com.sistemagestaoacademica.models.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor,Long> {
}
