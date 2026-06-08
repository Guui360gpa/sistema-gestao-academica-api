package br.com.sistemagestaoacademica.models;

import jakarta.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "cursos")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome",nullable = false)
    private String nome;

    @Column(name = "descricao", length = 500)
    private String descricao;

    @Column(name = "carga_horaria",nullable = false)
    private Integer cargaHoraria;

    @OneToMany(mappedBy = "curso")
    private List<Turma> turmas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(Integer cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    @Override
    public String toString() {
        return nome + "\n" +
                "descricao: " + descricao + "\n" +
                "cargaHoraria: " + cargaHoraria + "h";
    }
}
