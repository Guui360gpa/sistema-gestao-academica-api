package br.com.sistemagestaoacademica.repository;

import br.com.sistemagestaoacademica.models.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaRepository extends JpaRepository<Matricula,Long> {
}
