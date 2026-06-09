package br.com.sistemagestaoacademica;

import br.com.sistemagestaoacademica.principal.Main;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SistemagestaoacademicaApplication implements CommandLineRunner {

	public static void main(String[] args){
		SpringApplication.run(SistemagestaoacademicaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main();
		main.menu();
	}
}
