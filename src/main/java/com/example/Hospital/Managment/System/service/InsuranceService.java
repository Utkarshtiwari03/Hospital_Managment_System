package com.example.Hospital.Managment.System.service;

import com.example.Hospital.Managment.System.entity.Insurance;
import com.example.Hospital.Managment.System.entity.Patient;
import com.example.Hospital.Managment.System.repository.InsuranceRepository;
import com.example.Hospital.Managment.System.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InsuranceService {

    @Autowired
    private InsuranceRepository insuranceRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Transactional
    public Patient assignInsuranceToPatient(Insurance insurance,Long patientId){
        Patient patient= patientRepository.findById(patientId)
                .orElseThrow(()->new EntityNotFoundException("Patient not found with id: "+patientId));

        patient.setInsurance(insurance);
        insurance.setPatient(patient);
        return patient;
    }

    //remove insurance from the patient
    @Transactional
    public Patient disaccocoateInsuranceFromPatient(Long patientId){
        Patient patient=patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Patient not FOund with id: "+patientId));

         patient.setInsurance(null);
         return patient;
    }
}
