package com.example.Hospital.Managment.System.service;

import com.example.Hospital.Managment.System.dto.PatientResponseDto;
import com.example.Hospital.Managment.System.entity.Patient;
import com.example.Hospital.Managment.System.repository.PatientRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public PatientResponseDto getPatientById(Long id){
        Patient patient= patientRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Patient not found with id: "+id));

        return modelMapper.map(patient, PatientResponseDto.class);
    }

     public List<PatientResponseDto> getAllPatients(Integer pageNumber, Integer pageSize) {
        return patientRepository.findAllPatients(PageRequest.of(pageNumber, pageSize))
                .stream()
                .map(patient -> modelMapper.map(patient, PatientResponseDto.class))
                .collect(Collectors.toList());
    }
}
