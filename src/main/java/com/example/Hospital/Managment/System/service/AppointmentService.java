package com.example.Hospital.Managment.System.service;

import com.example.Hospital.Managment.System.entity.Appointment;
import com.example.Hospital.Managment.System.entity.Doctor;
import com.example.Hospital.Managment.System.entity.Patient;
import com.example.Hospital.Managment.System.repository.AppointmentRepository;
import com.example.Hospital.Managment.System.repository.DoctorRepository;
import com.example.Hospital.Managment.System.repository.PatientRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Transactional
    public Appointment createNewAppointment(Appointment appointment, Long doctortId, Long PatientId){
        Doctor doctor=doctorRepository.findById(doctortId).orElseThrow();
        Patient patient=patientRepository.findById(PatientId).orElseThrow();

        if(appointment.getId()!=null) throw new IllegalArgumentException("Assignment should not have Id");

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        patient.getAppointments().add(appointment);// to maintain bidirectional consistency
        doctor.getAppointments().add(appointment);

        return appointmentRepository.save(appointment);
    }

    @Transactional
    public Appointment reAssignAppointmentToAnotherDoctor(Long appointmentId,Long doctorId){
        Appointment appointment=appointmentRepository.findById(appointmentId).orElseThrow();
        Doctor doctor=doctorRepository.findById(doctorId).orElseThrow();

        appointment.setDoctor(doctor);// this will automatically call the update
        doctor.getAppointments().add(appointment);
        return appointment;
    }
}
