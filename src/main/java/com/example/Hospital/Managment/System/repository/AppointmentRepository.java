package com.example.Hospital.Managment.System.repository;

import com.example.Hospital.Managment.System.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}