package com.clinica.backend.repository;

import com.clinica.backend.entities.Consulta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.UUID;

public interface ConsultaRepository extends JpaRepository<Consulta, UUID> {


    @Query("""
       SELECT c FROM Consulta c
       WHERE (:codMedico IS NULL OR c.codmedico = :codMedico)
         AND (:codPaciente IS NULL OR c.codpaciente = :codPaciente)
         AND (:de IS NULL OR c.horario >= :de)
         AND (:ate IS NULL OR c.horario <= :ate)
       """)
    Page<Consulta> buscarComFiltros(
            @Param("codMedico") String codMedico,
            @Param("codPaciente") String codPaciente,
            @Param("de") Date de,
            @Param("ate") Date ate,
            Pageable pageable
    );
}
