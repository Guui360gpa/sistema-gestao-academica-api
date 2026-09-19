package br.com.sistemagestaoacademica.config;

import br.com.sistemagestaoacademica.models.Role;
import br.com.sistemagestaoacademica.models.Usuario;
import br.com.sistemagestaoacademica.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminSeeder implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${admin.email}")
    private String adminEmail;

    @Value("${admin.senha}")
    private String adminSenha;

    @Override
    public void run(String... args) throws Exception {
        if (!usuarioRepository.existsByRole(Role.ADMIN)) {
            Usuario admin = new Usuario(adminEmail, passwordEncoder.encode(adminSenha), Role.ADMIN);
            usuarioRepository.save(admin);
            System.out.println("Usuário ADMIN criado com sucesso: " + adminEmail);
        }
    }
}
