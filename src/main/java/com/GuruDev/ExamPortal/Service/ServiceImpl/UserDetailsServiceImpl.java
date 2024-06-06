package com.GuruDev.ExamPortal.Service.ServiceImpl;

import com.GuruDev.ExamPortal.Model.User;
import com.GuruDev.ExamPortal.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = this.userRepo.findByUserName(username);

        if (user == null)
        {
            throw new UsernameNotFoundException("No User Found");
        }


        return user;
    }
}
