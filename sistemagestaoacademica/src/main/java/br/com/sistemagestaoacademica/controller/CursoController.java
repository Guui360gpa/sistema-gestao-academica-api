package br.com.sistemagestaoacademica.controller;

import br.com.sistemagestaoacademica.dto.CursoRequestDto;
import br.com.sistemagestaoacademica.dto.CursoResponseDto;
import br.com.sistemagestaoacademica.service.curso.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
@RequiredArgsConstructor
public class CursoController{

    private final CadastrarCurso cadastrarCurso;
    private final DesativarCurso desativarCurso;
    private final ListarCursosDesativados listarDesativados;
    private final ListarCursosAtivos listarAtivos;
    private final AtivarCurso ativarCurso;

    @PostMapping
    public ResponseEntity<CursoResponseDto> cadastro(@Valid @RequestBody CursoRequestDto dto){
        CursoResponseDto cursoResponse = cadastrarCurso.cadastrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoResponse);
    }

    @GetMapping("/desativados")
    public ResponseEntity<List<CursoResponseDto>> listaDesativos(){
        List<CursoResponseDto> cursosResponse = listarDesativados.listar();
        return ResponseEntity.ok(cursosResponse);
    }

    @GetMapping("/ativados")
    public ResponseEntity<List<CursoResponseDto>> listaAtivos(){
        List<CursoResponseDto> cursosResponse = listarAtivos.listar();
        return ResponseEntity.ok(cursosResponse);
    }

    @PatchMapping("/{cursoId}/desativar")
    public ResponseEntity<CursoResponseDto> desativa(@PathVariable Long cursoId){
        CursoResponseDto cursoResponse = desativarCurso.desativar(cursoId);
        return ResponseEntity.ok(cursoResponse);
    }

    @PatchMapping("/{cursoId}/ativar")
    public ResponseEntity<CursoResponseDto> ativa(@PathVariable Long cursoId){
        CursoResponseDto cursoResponse = ativarCurso.ativar(cursoId);
        return ResponseEntity.ok(cursoResponse);
    }
}
