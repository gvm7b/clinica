package com.clinica.backend.controllers;

import com.clinica.backend.dto.MedicoDTO;
import com.clinica.backend.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/medico")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @GetMapping
    public ResponseEntity<List<MedicoDTO>> getAllMedicos() {
        return ResponseEntity.ok(medicoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoDTO> getMedicoById(@PathVariable UUID id){
        return ResponseEntity.ok(medicoService.getOne(id));
    }

    @PostMapping
    public ResponseEntity<MedicoDTO> createMedico(@RequestBody MedicoDTO medicoDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(medicoService.create(medicoDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicoDTO> updateMedico(@PathVariable UUID id, @RequestBody MedicoDTO medicoDTO){
        return ResponseEntity.ok(medicoService.update(id, medicoDTO));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedico(@PathVariable UUID id) {
        medicoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
