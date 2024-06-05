package com.GuruDev.ExamPortal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.GuruDev.ExamPortal.Model.Role;

public interface RoleRepo extends JpaRepository< Role , Long>{
    
}
