package com.crud.esig.enuns;

public enum Status
{
    PENDENTE("Pendente"),
    INICIADA("Em andamento"),
    CONCLUIDA("Concluída");

    private final String apelido;

    Status(String apelido) {
        this.apelido = apelido;
    }

    public String getLabel() {
        return apelido;
    }
}
