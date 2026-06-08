package br.com.sistemagestaoacademica.models;

public enum Especialidade {

    MATEMATICA("Matemática"),
    PORTUGUES("Português"),
    GEOGRAFIA("Geografia"),
    HISTORIA("História"),
    BIOLOGIA("Biologia"),
    QUIMICA("Química"),
    FISICA("Física"),
    INGLES("Inglês"),
    ESPANHOL("Espanhol");

    private String especialidade;

    Especialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    @Override
    public String toString() {
        return especialidade;
    }
}
