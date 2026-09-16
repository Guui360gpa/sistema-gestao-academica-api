package br.com.sistemagestaoacademica.controller;


import br.com.sistemagestaoacademica.dto.AlunoRequestDto;
import br.com.sistemagestaoacademica.dto.AlunoResponseDto;
import br.com.sistemagestaoacademica.dto.MatriculaRequestDto;
import br.com.sistemagestaoacademica.dto.MatriculaResponseDto;
import br.com.sistemagestaoacademica.service.aluno.CadastrarAluno;
import br.com.sistemagestaoacademica.service.aluno.ListarAlunoPorTurma;
import br.com.sistemagestaoacademica.service.aluno.MatricularAluno;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
@RequiredArgsConstructor
public class AlunoController{

    private final CadastrarAluno cadastrarAluno;
    private final ListarAlunoPorTurma listarAlunoPorTurma;
    private final MatricularAluno matricularAluno;


    @PostMapping
    public ResponseEntity<AlunoResponseDto> cadastro(@Valid @RequestBody AlunoRequestDto dto){
        AlunoResponseDto alunoResponse = cadastrarAluno.cadastrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoResponse);
    }

    @GetMapping("/turma/{turmaId}")
    public ResponseEntity<List<AlunoResponseDto>> listaPorTurma(@PathVariable Long turmaId){
        List<AlunoResponseDto> alunosResponse = listarAlunoPorTurma.listar(turmaId);
        return ResponseEntity.ok(alunosResponse);
    }

    @PostMapping("/matricula")
    public ResponseEntity<MatriculaResponseDto> matricula(@Valid @RequestBody MatriculaRequestDto dto){
        MatriculaResponseDto matriculaResponse = matricularAluno.matricular(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(matriculaResponse);
    }
}
