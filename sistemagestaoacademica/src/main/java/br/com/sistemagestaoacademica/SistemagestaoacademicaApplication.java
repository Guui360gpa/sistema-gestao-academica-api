package br.com.sistemagestaoacademica;

import br.com.sistemagestaoacademica.principal.MenuPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SistemagestaoacademicaApplication implements CommandLineRunner {

	@Autowired
	private MenuPrincipal menuPrincipal;

	public static void main(String[] args){
		SpringApplication.run(SistemagestaoacademicaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		menuPrincipal.menu();
	}
}
