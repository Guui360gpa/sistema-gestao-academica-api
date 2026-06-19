package br.com.sistemagestaoacademica.models;

public enum Status {

    ATIVADA("ativada"),
    DESATIVADA("desativada");


    private String status;

    Status(String statusTurma) {
        this.status = statusTurma;
    }

    public static Status fromValor(String valor) {
        for (Status s : values()) {
            if (s.status.equalsIgnoreCase(valor)) {
                return s;
            }
        }
        throw new IllegalArgumentException("Status da turma inválida inválida: " + valor);
    }

    @Override
    public String toString() {
        return status;
    }
}
