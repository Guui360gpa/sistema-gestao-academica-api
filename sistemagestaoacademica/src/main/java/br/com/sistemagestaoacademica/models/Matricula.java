package br.com.sistemagestaoacademica.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(
        name = "matriculas",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"id_aluno", "id_turma"}
                )
        }
)
public class Matricula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_aluno", nullable = false)
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "id_turma", nullable = false)
    private Turma turma;

    @Column(name = "data_matricula", nullable = false)
    private LocalDate dataMatricula;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public LocalDate getDataMatricula() {
        return dataMatricula;
    }

    public void setDataMatricula(LocalDate dataMatricula) {
        this.dataMatricula = dataMatricula;
    }

    @Override
    public String toString() {
        return "Matricula{" +
                "id=" + id +
                ", aluno=" + aluno.getNome() +
                ", turma=" + turma.getId() +
                ", dataMatricula=" + dataMatricula +
                '}';
    }
}
