package com.example.Hospital.Managment.System.repository;

import com.example.Hospital.Managment.System.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}