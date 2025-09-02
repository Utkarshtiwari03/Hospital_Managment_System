package com.example.Hospital.Managment.System.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 100)
    private String name;

    @Column(length=100)
    private String specialization;

    @Column(nullable=false,unique = true,length = 100)
    private String email;

    @OneToMany(mappedBy = "doctor")
    private List<Appointment> appointments=new ArrayList<>();

    @OneToOne(mappedBy="headDoctor")
    private Department department;

    @ManyToMany(mappedBy = "doctors")
    private Set<Department> departments=new HashSet<>();
}
