package com.clinica.backend.repository;

import com.clinica.backend.entities.Consulta;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import java.util.Date;
import java.util.UUID;

public interface ConsultaRepository extends JpaRepository<Consulta, UUID> {

    Page<Consulta> findByCodmedicoContainingIgnoreCase(String codmedico, Pageable pageable);
    Page<Consulta> findByCodpacienteContainingIgnoreCase(String codpaciente, Pageable pageable);
    Page<Consulta> findByHorarioBetween(Date inicio, Date fim, Pageable pageable);

}
