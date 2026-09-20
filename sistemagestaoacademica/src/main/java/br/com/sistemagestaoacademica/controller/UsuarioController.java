package br.com.sistemagestaoacademica.controller;

import br.com.sistemagestaoacademica.dto.UsuarioResponseDto;
import br.com.sistemagestaoacademica.dto.UsuarioResquestDto;
import br.com.sistemagestaoacademica.models.Status;
import br.com.sistemagestaoacademica.service.usuario.CadastrarUsuario;
import br.com.sistemagestaoacademica.service.usuario.DesativarUsuario;
import br.com.sistemagestaoacademica.service.usuario.EditarUsuario;
import br.com.sistemagestaoacademica.service.usuario.ListarUsuarios;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class UsuarioController {

    private final CadastrarUsuario cadastrarUsuario;
    private final ListarUsuarios listarUsuarios;
    private final EditarUsuario editarUsuario;
    private final DesativarUsuario desativarUsuario;

    @PostMapping
    public ResponseEntity<UsuarioResponseDto> cadastro(@Valid @RequestBody UsuarioResquestDto dto){
        UsuarioResponseDto usuarioResponse = cadastrarUsuario.cadastrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioResponse);
    }

    @GetMapping("/ativos")
    public ResponseEntity<List<UsuarioResponseDto>> listaAtivos(){
        List<UsuarioResponseDto> usuarioResponseList = listarUsuarios.listar(Status.ATIVADA);
        return ResponseEntity.ok(usuarioResponseList);
    }

    @GetMapping("/desativados")
    public ResponseEntity<List<UsuarioResponseDto>> listaDesativos(){
        List<UsuarioResponseDto> usuarioResponseList = listarUsuarios.listar(Status.DESATIVADA);
        return ResponseEntity.ok(usuarioResponseList);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> edita(@PathVariable Long id,@Valid @RequestBody UsuarioResquestDto dto){
        UsuarioResponseDto usuarioResponse = editarUsuario.editar(id,dto);
        return ResponseEntity.ok(usuarioResponse);
    }

    @PatchMapping("/{id}/desativar")
    public ResponseEntity<UsuarioResponseDto> desativa(@PathVariable Long id) {
        UsuarioResponseDto usuarioResponse = desativarUsuario.desativar(id);
        return ResponseEntity.ok(usuarioResponse);
    }
}
