package com.clinica.backend.controllers;

import com.clinica.backend.dto.ConsultaDTO;
import com.clinica.backend.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @GetMapping
    public ResponseEntity<Page<ConsultaDTO>> getConsultas(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "horario") String sort,
            @RequestParam(defaultValue = "asc") String dir,
            @RequestParam(required = false) String codMedico,
            @RequestParam(required = false) String codPaciente,
            @RequestParam(required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date de,
            @RequestParam(required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date ate
    ) {

        Page<ConsultaDTO> result = consultaService.getAll(
                page,
                size,
                sort,
                dir,
                codMedico,
                codPaciente,
                de,
                ate
        );

        return ResponseEntity.ok(result);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ConsultaDTO> getConsultaById(@PathVariable UUID id) {
        return ResponseEntity.ok(consultaService.getOne(id));
    }

    @PostMapping
    public ResponseEntity<ConsultaDTO> createConsulta(@RequestBody ConsultaDTO consultaDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(consultaService.create(consultaDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultaDTO> updateConsulta(
            @PathVariable UUID id,
            @RequestBody ConsultaDTO consultaDTO
    ) {
        return ResponseEntity.ok(consultaService.update(id, consultaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsulta(@PathVariable UUID id) {
        consultaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
