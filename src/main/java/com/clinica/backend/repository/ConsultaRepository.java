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


    @Query(
            "SELECT c FROM Consulta c " +
                    "WHERE (:codMedico IS NULL OR LOWER(c.codmedico) LIKE LOWER(CONCAT('%', :codMedico, '%'))) " +
                    "  AND (:codPaciente IS NULL OR LOWER(c.codpaciente) LIKE LOWER(CONCAT('%', :codPaciente, '%'))) " +
                    "  AND (:de IS NULL OR c.horario >= :de) " +
                    "  AND (:ate IS NULL OR c.horario <= :ate)"
    )
    Page<Consulta> buscarComFiltros(
            @Param("codMedico") String codMedico,
            @Param("codPaciente") String codPaciente,
            @Param("de") Date de,
            @Param("ate") Date ate,
            Pageable pageable
    );
}
