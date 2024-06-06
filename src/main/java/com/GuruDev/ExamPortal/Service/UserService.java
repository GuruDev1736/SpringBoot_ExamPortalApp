package com.GuruDev.ExamPortal.Service;

import java.util.Set;

import com.GuruDev.ExamPortal.Model.User;
import com.GuruDev.ExamPortal.Model.UserRole;

public interface UserService {

    public User createUser(User user, Set<UserRole> userRoles) throws Exception;

    public User getUser(String userName);

    public User updateUser(User user, Long userId);

    public void deleteUser(Long userId);

}
