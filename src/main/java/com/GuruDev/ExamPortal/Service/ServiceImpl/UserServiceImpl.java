package com.GuruDev.ExamPortal.Service.ServiceImpl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.GuruDev.ExamPortal.Model.User;
import com.GuruDev.ExamPortal.Model.UserRole;
import com.GuruDev.ExamPortal.Repository.RoleRepo;
import com.GuruDev.ExamPortal.Repository.UserRepo;
import com.GuruDev.ExamPortal.Service.UserService;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo; 
    
    @Autowired
    private RoleRepo roleRepo;


    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {

        User local = this.userRepo.findByUserName(user.getUserName());
        if (local!=null)
        {
            System.out.printf("User is already present");
            throw  new Exception("User Already Present");
        }
        else
        {
            for (UserRole userRole : userRoles){
                roleRepo.save(userRole.getRole());
            }

            user.getUserRoles().addAll(userRoles);
            local = this.userRepo.save(user);
        }

        return local;
        
    }

    @Override
    public User getUser(String userName) {
        return this.userRepo.findByUserName(userName);
    }

}
