package br.com.sistemagestaoacademica.service.aluno;

import br.com.sistemagestaoacademica.models.Aluno;
import br.com.sistemagestaoacademica.service.BaseService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

@org.springframework.stereotype.Service
public class CadastrarAluno extends BaseService {
    public void cadastrar() {
        System.out.println("Digite o nome completo do Aluno:");
        var nomeAluno = read.nextLine();

        System.out.printf("Qual é a data de nascimento do %s:", nomeAluno);
        var dataNascimento = read.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataNascimentoFormat = LocalDate.parse(dataNascimento, formatter);

        System.out.printf("Qual é o email do %s:", nomeAluno);
        var email = read.nextLine();

        String emailRegex = "^[a-zA-Z0-9._%+\\-]+@[a-zA-Z0-9.\\-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);

        while (!pattern.matcher(email).matches()) {
            System.out.print("Email inválido! Digite um email válido (ex: usuario@dominio.com): ");
            email = read.nextLine();
        }

        Aluno aluno = new Aluno(nomeAluno, dataNascimentoFormat, email);

        alunoRepository.save(aluno);

        System.out.println("Aluno cadastrado com sucesso!");
    }
}
