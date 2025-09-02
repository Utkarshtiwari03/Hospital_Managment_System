package com.example.Hospital.Managment.System.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CreateAppointmentRequestDto {
    private Long doctorId;
    private Long patientId;
    private LocalDateTime appointmentTime;
    private String reason;
}
