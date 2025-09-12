package com.example.Hospital.Managment.System;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.Hospital.Managment.System.entity.Patient;
import com.example.Hospital.Managment.System.repository.PatientRepository;

import lombok.RequiredArgsConstructor;

@SpringBootTest
@RequiredArgsConstructor
public class PatientTests {

    private final PatientRepository patientRepository;
    
    @Test
    public void testPatientRepository(){

        List<Patient> patientList=patientRepository.findAll();
        System.out.println(patientList);
    }
}
