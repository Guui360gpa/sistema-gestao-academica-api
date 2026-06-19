package br.com.sistemagestaoacademica.repository;

import br.com.sistemagestaoacademica.models.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlunoRepository extends JpaRepository<Aluno,Long> {

    @Query("SELECT a FROM Aluno a WHERE LOWER(a.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<Aluno> buscarAlunoPorNome(@Param("nome") String nome);
}
