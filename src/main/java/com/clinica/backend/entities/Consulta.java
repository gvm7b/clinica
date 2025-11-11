package com.clinica.backend.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "consulta")
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    private String codpaciente;
    private String codmedico;
    private Date horario;

    public Consulta(String codpaciente, String codmedico, Date horario) {
        this.codpaciente = codpaciente;
        this.codmedico = codmedico;
        this.horario = horario;
    }

    public UUID getId() {return id;}

}
