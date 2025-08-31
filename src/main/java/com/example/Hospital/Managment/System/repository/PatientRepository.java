package com.example.Hospital.Managment.System.repository;

import com.example.Hospital.Managment.System.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,Long> {

}
