package br.com.sistemagestaoacademica.controller;


import br.com.sistemagestaoacademica.dto.ProfessorRequestDto;
import br.com.sistemagestaoacademica.dto.ProfessorResponseDto;
import br.com.sistemagestaoacademica.service.professor.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professores")
@RequiredArgsConstructor
public class ProfessorController{

    private final CadastrarProfessor cadastrarProfessor;
    private final ListarProfessoresAtivos listarAtivos;
    private final DesativarProfessor desativarProfessor;
    private final AtivarProfessor ativarProfessor;
    private final ListarProfessoresDesativados listarDesativados;

    @PostMapping
    public ResponseEntity<ProfessorResponseDto> cadastro(@Valid @RequestBody ProfessorRequestDto dto){
        ProfessorResponseDto professorResponse = cadastrarProfessor.cadastrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(professorResponse);
    }

    @GetMapping("/ativados")
    public ResponseEntity<List<ProfessorResponseDto>> listaAtivos(){
        List<ProfessorResponseDto> professoresResponse = listarAtivos.listar();
        return ResponseEntity.ok(professoresResponse);
    }

    @GetMapping("/desativados")
    public ResponseEntity<List<ProfessorResponseDto>> listaDesativados() {
        List<ProfessorResponseDto> professoresResponse = listarDesativados.listar();
        return ResponseEntity.ok(professoresResponse);
    }

    @PatchMapping("/{professorId}/desativar")
    public ResponseEntity<ProfessorResponseDto> desativar(@PathVariable Long professorId) {
        ProfessorResponseDto professorResponse = desativarProfessor.desativar(professorId);
        return ResponseEntity.ok(professorResponse);
    }

    @PatchMapping("/{professorId}/ativar")
    public ResponseEntity<ProfessorResponseDto> ativar(@PathVariable Long professorId) {
        ProfessorResponseDto professorResponse = ativarProfessor.ativar(professorId);
        return ResponseEntity.ok(professorResponse);
    }
}
