package com.clinica.backend.service;

import com.clinica.backend.dto.PacienteDTO;
import com.clinica.backend.entities.Paciente;
import com.clinica.backend.repository.PacienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepository pacienteRepository;

    private PacienteDTO toDTO(Paciente paciente){
        return new PacienteDTO(paciente.getId(), paciente.getNome(), paciente.getCpf(), paciente.getTelefone());
    }

    private Paciente toEntity(PacienteDTO paciente) {
        return new Paciente(paciente.getNome(), paciente.getCpf(), paciente.getTelefone());
    }

    public PacienteDTO getOne(UUID id) {
        Paciente paciente = pacienteRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Paciente não encontrado."));
        return toDTO(paciente);
    }

    public List<PacienteDTO> getAll() {
        return pacienteRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public PacienteDTO create(PacienteDTO dto) {
        Paciente paciente = toEntity(dto);
        return toDTO(pacienteRepository.save(paciente));
    }

    public PacienteDTO update(UUID id, PacienteDTO dto) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Paciente não encontrado."));

        paciente.setNome(dto.getNome());
        paciente.setCpf(dto.getCpf());
        paciente.setTelefone(dto.getTelefone());

        return toDTO(pacienteRepository.save(paciente));
    }

    public PacienteDTO delete(UUID id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Paciente não encontrado."));

        pacienteRepository.deleteById(id);
        return toDTO(paciente);
    }

}
