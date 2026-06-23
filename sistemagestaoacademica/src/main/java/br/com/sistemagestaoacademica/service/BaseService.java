package br.com.sistemagestaoacademica.service;


import br.com.sistemagestaoacademica.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Scanner;

public abstract class BaseService {
    @Autowired
    protected AlunoRepository alunoRepository;
    @Autowired
    protected CursoRepository cursoRepository;
    @Autowired
    protected MatriculaRepository matriculaRepository;
    @Autowired
    protected ProfessorRepository professorRepository;
    @Autowired
    protected TurmaRepository turmaRepository;

    protected Scanner read = new Scanner(System.in);

    protected Long lerLong() {
        while (true) {
            try {
                return Long.parseLong(read.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida! Digite apenas números: ");
            }
        }
    }
}
