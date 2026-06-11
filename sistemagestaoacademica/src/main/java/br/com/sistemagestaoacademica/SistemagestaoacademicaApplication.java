package br.com.sistemagestaoacademica;

import br.com.sistemagestaoacademica.controller.Controller;
import br.com.sistemagestaoacademica.repository.AlunoRepository;
import br.com.sistemagestaoacademica.repository.ProfessorRepository;
import br.com.sistemagestaoacademica.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SistemagestaoacademicaApplication implements CommandLineRunner {

	@Autowired
	private AlunoRepository alunoRepository;

	@Autowired
	private ProfessorRepository professorRepository;

	@Autowired
	private TurmaRepository turmaRepository;

	public static void main(String[] args){
		SpringApplication.run(SistemagestaoacademicaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Controller controller = new Controller(alunoRepository,professorRepository,turmaRepository);
		controller.menu();
	}
}
