package com.GuruDev.ExamPortal.Controllers;


import com.GuruDev.ExamPortal.Exceptions.ResourceNotFoundException;
import com.GuruDev.ExamPortal.Model.*;
import com.GuruDev.ExamPortal.Repository.UserRepo;
import com.GuruDev.ExamPortal.Security.JwtHelper;
import com.GuruDev.ExamPortal.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtHelper helper;

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/login")
    public ResponseEntity<JWTResponse> login(@RequestBody JWTRequest request) {

        this.doAuthenticate(request.getUserName(), request.getPassword());

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUserName());
        String token = this.helper.generateToken(userDetails);

        User user = this.userRepo.findByUserName(userDetails.getUsername());

        JWTResponse response = JWTResponse.builder()
                .token(token)
                .userId(user.getId())
                .userRole(userDetails.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .findFirst()
                        .orElse(""))
                .userName(userDetails.getUsername()).build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/register")
    public User createUser(@RequestBody User user) throws Exception {

        Set<UserRole> userRoles = new HashSet<>();
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role role  = new Role();
        role.setRoleId(1L);
        role.setRoleName("NORMAL");

        UserRole userRole = new UserRole();
        userRole.setRole(role);
        userRole.setUser(user);

        userRoles.add(userRole);

        return this.userService.createUser(user,userRoles);

    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);

        } catch (BadCredentialsException e) {
           e.printStackTrace();
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }

}
