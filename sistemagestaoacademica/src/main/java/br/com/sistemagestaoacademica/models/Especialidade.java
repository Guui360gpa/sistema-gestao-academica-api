package br.com.sistemagestaoacademica.models;

public enum Especialidade {

    MATEMATICA("matematica"),
    PORTUGUES("portugues"),
    GEOGRAFIA("geografia"),
    HISTORIA("historia"),
    BIOLOGIA("biologia"),
    QUIMICA("quimica"),
    FISICA("fisica"),
    INGLES("ingles"),
    ESPANHOL("espanhol");

    private String especialidade;

    Especialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public static Especialidade fromValor(String valor) {
        for (Especialidade e : values()) {
            if (e.especialidade.equalsIgnoreCase(valor)) {
                return e;
            }
        }
        throw new IllegalArgumentException("Especialidade inválida: " + valor);
    }


    @Override
    public String toString() {
        return especialidade;
    }
}
