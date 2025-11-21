package com.clinica.backend.controllers;

import com.clinica.backend.dto.ConsultaDTO;
import com.clinica.backend.dto.PacienteDTO;
import com.clinica.backend.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {
    @Autowired
    private ConsultaService consultaService;

    @GetMapping
    public ResponseEntity<List<ConsultaDTO>> getConsultas() { return ResponseEntity.ok(consultaService.getAll());}

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaDTO> getConsultaById(@PathVariable UUID id){
        return ResponseEntity.ok(consultaService.getOne(id));
    }

    @PostMapping
    public ResponseEntity<ConsultaDTO> createConsulta(@RequestBody ConsultaDTO consultaDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(consultaService.create(consultaDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultaDTO> updateConsulta(@PathVariable UUID id, @RequestBody ConsultaDTO consultaDTO) {
        return ResponseEntity.ok(consultaService.update(id, consultaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsulta(@PathVariable UUID id) {
        consultaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
