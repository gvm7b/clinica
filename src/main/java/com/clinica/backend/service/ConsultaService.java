package com.clinica.backend.service;

import com.clinica.backend.dto.ConsultaDTO;
import com.clinica.backend.entities.Consulta;
import com.clinica.backend.entities.Medico;
import com.clinica.backend.entities.Paciente;
import com.clinica.backend.repository.ConsultaRepository;
import com.clinica.backend.repository.MedicoRepository;
import com.clinica.backend.repository.PacienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    private ConsultaDTO toDTO(Consulta consulta) {
        return new ConsultaDTO(
                consulta.getId(),
                consulta.getCodmedico(),
                consulta.getCodpaciente(),
                consulta.getHorario()
        );
    }

    private Consulta toEntity(ConsultaDTO dto) {
        return new Consulta(
                dto.getCodpaciente(),
                dto.getCodmedico(),
                dto.getHorario()
        );
    }

    public ConsultaDTO getOne(UUID id) {
        Consulta c = consultaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Consulta não encontrada."));
        return toDTO(c);
    }


    public Page<ConsultaDTO> getAll(
            int page,
            int size,
            String sort,
            String dir,
            String codMedico,
            String codPaciente,
            Date de,
            Date ate
    ) {
        Sort.Direction direction = "desc".equalsIgnoreCase(dir)
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sort));

        Page<Consulta> result = consultaRepository.buscarComFiltros(
                (codMedico == null || codMedico.isBlank()) ? null : codMedico,
                (codPaciente == null || codPaciente.isBlank()) ? null : codPaciente,
                de,
                ate,
                pageable
        );

        return result.map(this::toDTO);
    }

    public ConsultaDTO create(ConsultaDTO dto) {

        UUID pacienteId;
        UUID medicoId;

        try {
            pacienteId = UUID.fromString(dto.getCodpaciente());
        } catch (IllegalArgumentException e) {
            throw new EntityNotFoundException("Código de paciente inválido.");
        }

        try {
            medicoId = UUID.fromString(dto.getCodmedico());
        } catch (IllegalArgumentException e) {
            throw new EntityNotFoundException("Código de médico inválido.");
        }

        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new EntityNotFoundException("Paciente não cadastrado."));

        Medico medico = medicoRepository.findById(medicoId)
                .orElseThrow(() -> new EntityNotFoundException("Médico não cadastrado."));

        // monta entidade consulta
        Consulta consulta = toEntity(dto);
        consulta.setPaciente(paciente);
        consulta.setMedico(medico);

        return toDTO(consultaRepository.save(consulta));
    }

    public ConsultaDTO update(UUID id, ConsultaDTO dto) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Consulta não encontrada."));

        UUID pacienteId;
        UUID medicoId;

        try {
            pacienteId = UUID.fromString(dto.getCodpaciente());
        } catch (IllegalArgumentException e) {
            throw new EntityNotFoundException("Código de paciente inválido.");
        }

        try {
            medicoId = UUID.fromString(dto.getCodmedico());
        } catch (IllegalArgumentException e) {
            throw new EntityNotFoundException("Código de médico inválido.");
        }

        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new EntityNotFoundException("Paciente não cadastrado."));

        Medico medico = medicoRepository.findById(medicoId)
                .orElseThrow(() -> new EntityNotFoundException("Médico não cadastrado."));

        consulta.setCodmedico(dto.getCodmedico());
        consulta.setCodpaciente(dto.getCodpaciente());
        consulta.setHorario(dto.getHorario());
        consulta.setPaciente(paciente);
        consulta.setMedico(medico);

        return toDTO(consultaRepository.save(consulta));
    }

    public ConsultaDTO delete(UUID id) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Consulta não encontrada."));

        consultaRepository.delete(consulta);
        return toDTO(consulta);
    }
}
