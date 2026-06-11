package br.com.sistemagestaoacademica.repository;

import br.com.sistemagestaoacademica.models.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno,Long> {
}
