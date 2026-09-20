package br.com.sistemagestaoacademica.controller;

import br.com.sistemagestaoacademica.dto.LoginRequestDto;
import br.com.sistemagestaoacademica.dto.LoginResponseDto;
import br.com.sistemagestaoacademica.service.login.AutenticarUsuario;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AutenticarUsuario autenticarUsuario;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto dto) {
        LoginResponseDto loginResponse = autenticarUsuario.login(dto);
        return ResponseEntity.ok(loginResponse);
    }

}
