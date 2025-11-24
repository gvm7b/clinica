package com.clinica.backend.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public class PacienteDTO {
    private UUID id;

    @NotBlank(message = "O nome do paciente é obrigatório")
    private String nome;

    @NotBlank(message = "O CPF do paciente é obrigatório")
    private String cpf;

    @NotBlank(message = "O telefone do paciente é obrigatório")
    private String telefone;

    public PacienteDTO() {}

    public PacienteDTO(UUID id, String nome, String cpf, String telefone) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
