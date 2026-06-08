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


    public Long getRa() {
        return ra;
    }

    public void setRa(Long ra) {
        this.ra = ra;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private int getIdade(LocalDate dataNascimento){
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return ra +
                " - " + nome +
                " - " + getIdade(dataNascimento) + " anos \n";
    }
}
