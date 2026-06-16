package br.com.sistemagestaoacademica.models;

public enum StatusTurma {

    ATIVADA("ativada"),
    DESATIVADA("desativada");


    private String statusTurma;

    StatusTurma(String statusTurma) {
        this.statusTurma = statusTurma;
    }

    public static StatusTurma fromValor(String valor) {
        for (StatusTurma s : values()) {
            if (s.statusTurma.equalsIgnoreCase(valor)) {
                return s;
            }
        }
        throw new IllegalArgumentException("Status da turma inválida inválida: " + valor);
    }

    @Override
    public String toString() {
        return statusTurma;
    }
}
