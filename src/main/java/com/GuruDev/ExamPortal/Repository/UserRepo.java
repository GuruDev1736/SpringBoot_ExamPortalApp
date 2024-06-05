package com.GuruDev.ExamPortal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.GuruDev.ExamPortal.Model.User;

public interface UserRepo extends JpaRepository< User , Long> {

    public User findByUserName(String userName);

}
