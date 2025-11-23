package com.clinica.backend.service;

import com.clinica.backend.dto.ConsultaDTO;
import com.clinica.backend.entities.Consulta;
import com.clinica.backend.repository.ConsultaRepository;
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

    public List<ConsultaDTO> getAllSemPaginacao() {
        return consultaRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
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
        Consulta consulta = toEntity(dto);
        return toDTO(consultaRepository.save(consulta));
    }

    public ConsultaDTO update(UUID id, ConsultaDTO dto) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Consulta não encontrada."));

        consulta.setCodmedico(dto.getCodmedico());
        consulta.setCodpaciente(dto.getCodpaciente());
        consulta.setHorario(dto.getHorario());

        return toDTO(consultaRepository.save(consulta));
    }

    public ConsultaDTO delete(UUID id) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Consulta não encontrada."));

        consultaRepository.delete(consulta);
        return toDTO(consulta);
    }
}
