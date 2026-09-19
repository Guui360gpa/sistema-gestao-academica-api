package br.com.sistemagestaoacademica.repository;

import br.com.sistemagestaoacademica.models.Role;
import br.com.sistemagestaoacademica.models.Status;
import br.com.sistemagestaoacademica.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    boolean existsByEmail(String email);

    List<Usuario> findByStatus(@Param("status") Status status);

    Optional<Usuario> findByEmail (String email);

    boolean existsByRole(Role role);

}
