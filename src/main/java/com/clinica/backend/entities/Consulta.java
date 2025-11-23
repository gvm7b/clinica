package com.clinica.backend.entities;

import jakarta.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "consulta")
public class Consulta {

    @Id
    @GeneratedValue
    private UUID id;

    private String codpaciente;
    private String codmedico;
    private Date horario;

    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    // 🔹 Construtor vazio obrigatório para JPA
    public Consulta() {
    }

    // 🔹 Construtor usado no Service/DTO
    public Consulta(String codpaciente, String codmedico, Date horario) {
        this.codpaciente = codpaciente;
        this.codmedico = codmedico;
        this.horario = horario;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCodpaciente() {
        return codpaciente;
    }

    public void setCodpaciente(String codpaciente) {
        this.codpaciente = codpaciente;
    }

    public String getCodmedico() {
        return codmedico;
    }

    public void setCodmedico(String codmedico) {
        this.codmedico = codmedico;
    }

    public Date getHorario() {
        return horario;
    }

    public void setHorario(Date horario) {
        this.horario = horario;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}
