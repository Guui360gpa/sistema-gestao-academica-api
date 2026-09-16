package br.com.sistemagestaoacademica.controller;

import br.com.sistemagestaoacademica.dto.TurmaRequestDto;
import br.com.sistemagestaoacademica.dto.TurmaResponseDto;
import br.com.sistemagestaoacademica.service.turma.DesativarTurma;
import br.com.sistemagestaoacademica.service.turma.ListarTurmasAtivas;
import br.com.sistemagestaoacademica.service.turma.ListarTurmasDesativadas;
import br.com.sistemagestaoacademica.service.turma.NovaTurma;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/turmas")
@RequiredArgsConstructor
public class TurmaController{

    private final NovaTurma novaTurma;
    private final DesativarTurma desativarTurma;
    private final ListarTurmasAtivas listarAtivas;
    private final ListarTurmasDesativadas listarDesativadas;

    @PostMapping
    public ResponseEntity<TurmaResponseDto> nova(@Valid @RequestBody TurmaRequestDto dto){
        TurmaResponseDto turmaResponse = novaTurma.nova(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(turmaResponse);
    }

    @GetMapping("/ativadas")
    public ResponseEntity<List<TurmaResponseDto>> listaAtivas(){
        List<TurmaResponseDto> turmasResponse = listarAtivas.listar();
        return ResponseEntity.ok(turmasResponse);
    }

    @GetMapping("/desativadas")
    public ResponseEntity<List<TurmaResponseDto>> listaDesativas(){
        List<TurmaResponseDto> turmasResponse = listarDesativadas.listar();
        return ResponseEntity.ok(turmasResponse);
    }

    @PatchMapping("/{turmaId}/desativar")
    public ResponseEntity<TurmaResponseDto> desativar(@PathVariable Long turmaId) {
        TurmaResponseDto turmaResponse = desativarTurma.desativar(turmaId);
        return ResponseEntity.ok(turmaResponse);
    }
}
