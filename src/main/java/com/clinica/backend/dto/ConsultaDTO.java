package com.clinica.backend.dto;

import java.util.Date;
import java.util.UUID;

public class ConsultaDTO {

    private UUID id;
    private Integer codmedico;
    private Integer codpaciente;
    private Date horario;


    public ConsultaDTO(UUID id, Integer codmedico, Integer codpaciente, Date horario) {
        this.id = id;
        this.codmedico = codmedico;
        this.codpaciente = codpaciente;
        this.horario = horario;
    }

    public  UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
}
