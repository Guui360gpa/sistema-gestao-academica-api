package br.com.sistemagestaoacademica.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "professores")
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome",nullable = false)
    private String nome;

    @Column(name = "email",nullable = false)
    private String email;

    @Column(name = "telefone",nullable = false)
    private String telefone;

    @Column(name = "especialidade",nullable = false)
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @OneToMany(mappedBy = "professor")
    private List<Turma> turmas;

    @Column(name = "status_professor",nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    public Professor(String nome,String email,String telefone, Especialidade especialidade) {
        this.nome = nome;
        this.especialidade = especialidade;
        this.email = email;
        this.telefone = telefone;
        this.status = Status.ATIVADA;
    }

    public Professor() {}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }
}
