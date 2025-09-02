package com.example.Hospital.Managment.System.dto;

import java.time.LocalDate;

import com.example.Hospital.Managment.System.entity.type.BloodGroupType;

import lombok.Data;

@Data
public class PatientResponseDto {
    private Long id;
    private String name;
    private String gender;
    private LocalDate birthDate;
    private BloodGroupType bloodGroup;
}
