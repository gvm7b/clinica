package com.clinica.backend.service;

import com.clinica.backend.dto.MedicoDTO;
import com.clinica.backend.entities.Medico;
import com.clinica.backend.repository.MedicoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MedicoService {


    @Autowired
    MedicoRepository medicoRepository;


    private MedicoDTO toDTO(Medico medico){
        return new MedicoDTO(medico.getId(), medico.getNome(), medico.getCrm(), medico.getEspecialidade());
    }

    private Medico toEntity(MedicoDTO medico){
        return new Medico(medico.getNome(), medico.getCrm(), medico.getEspecialidade());
    }


    public MedicoDTO getOne(UUID id) {
        Medico medico = medicoRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Medico não encontrado."));
        return toDTO(medico);
    }

    public List<MedicoDTO> getAll() {
        return medicoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }


    public MedicoDTO create(MedicoDTO dto) {
        Medico medico = toEntity(dto);
        return toDTO(medicoRepository.save(medico));
    }


    public MedicoDTO update(UUID id, MedicoDTO dto) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Medico não encontrado."));

        medico.setNome(dto.getNome());
        medico.setCrm(dto.getCrm());
        medico.setEspecialidade(dto.getEspecialidade());

        return toDTO(medicoRepository.save(medico));
    }



    public MedicoDTO delete(UUID id){
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Medico não encontrado."));
        medicoRepository.delete(medico);
        return toDTO(medico);
    }

}
