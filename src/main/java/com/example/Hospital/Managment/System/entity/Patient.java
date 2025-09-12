package com.example.Hospital.Managment.System.entity;

import com.example.Hospital.Managment.System.entity.type.BloodGroupType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@ToString
@Getter
@Setter
@Table(
        name = "patient",
        uniqueConstraints = {
//                @UniqueConstraint(name = "unique_patient_email", columnNames = {"email"}),
                @UniqueConstraint(name = "unique_patient_name_birthdate", columnNames = {"name", "birthDate"})//db can't contain a person with saame name and birthdate i.e name can be same and birthday too but both can't be same 
        },
        indexes = {
                @Index(name = "idx_patient_birth_date", columnList = "birthDate")
        }
)
@Builder
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 40)//this column can't be null
    private String name;

    //    @ToString.Exclude
    private LocalDate birthDate;

    @Column(unique = true, nullable = false)// db will accept only unique email if any two email matches it will throw an error
    private String email;                   //this column can't be null

    private String gender;

    @OneToOne
    @MapsId
    private User user;

    @CreationTimestamp //automatic fill curent time in this column
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private BloodGroupType bloodGroup;

    @OneToOne(cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JoinColumn(name = "patient_insurance_id") // owning side
    private Insurance insurance;

    @OneToMany(mappedBy = "patient", cascade = {CascadeType.REMOVE}, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Appointment> appointments = new ArrayList<>();
}