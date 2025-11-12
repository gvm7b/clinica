package com.clinica.backend.dto;

import java.util.Date;
import java.util.UUID;

public class ConsultaDTO {

    private UUID id;
    private String codmedico;
    private String codpaciente;
    private Date horario;


    public ConsultaDTO(UUID id, String codmedico, String codpaciente, Date horario) {
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

    public String getCodmedico() {return codmedico;}
    public void setCodmedico(String codmedico) {this.codmedico = codmedico;}
    public String getCodpaciente() {return codpaciente;}
    public void setCodpaciente(String codpaciente) {this.codpaciente = codpaciente;}
    public Date getHorario() {return horario;}
    public void setHorario(Date horario) {this.horario = horario;}

}
