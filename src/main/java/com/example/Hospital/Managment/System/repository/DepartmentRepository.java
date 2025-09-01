package com.example.Hospital.Managment.System.repository;

import com.example.Hospital.Managment.System.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}