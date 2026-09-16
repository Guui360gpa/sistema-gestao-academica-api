package br.com.sistemagestaoacademica.models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;


@Entity
@Table(name = "alunos")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ra;

    @Column(name = "nome",nullable = false)
    private String nome;

    @Column(name = "data_nascimento",nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "email",nullable = false)
    private String email;

    @OneToMany(mappedBy = "aluno")
    private List<Matricula> matriculas;

    public Aluno(String nome, LocalDate dataNascimento, String email) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.email = email;
    }

    public Aluno() {}

    public Long getRa() {
        return ra;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    public int getIdade(){
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }

    public String getEmail() {
        return email;
    }

    private int getIdade(LocalDate dataNascimento){
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }
}
