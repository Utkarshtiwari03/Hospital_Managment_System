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
//@Data
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(
//        name="patient_tbl",  //set table name by ur own
        uniqueConstraints = {
//                @UniqueConstraint(name="Unique_patient_email",columnNames = {"email"}),//this will make sure that table contains only unique email
                @UniqueConstraint(name="unique_patient_name_dob", columnNames = {"name","birthDate"})//make sure that the combination od name & dob id unique
        }
)
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
//    @Column(name="patient_name")// change the column name to patient_name in sql table
    private LocalDate birthDate;
    @Column(unique = true,nullable =false)      //this will make sure that table contains only unique email and can't be null
    private String email;

    private String gender;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private BloodGroupType bloodGroup;

    @OneToOne(cascade = {CascadeType.ALL},orphanRemoval = true)
    @JoinColumn(name = "patient_insurance_id")//this will show change the name of join column
    private Insurance insurance;


    @OneToMany(mappedBy = "patient",cascade = CascadeType.REMOVE,orphanRemoval = true)
    @ToString.Exclude//inverse side
    private List<Appointment> appointments=new ArrayList<>();

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }


}
