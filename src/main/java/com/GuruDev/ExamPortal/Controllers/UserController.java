package com.GuruDev.ExamPortal.Controllers;

import com.GuruDev.ExamPortal.Model.Role;
import com.GuruDev.ExamPortal.Model.User;
import com.GuruDev.ExamPortal.Model.UserRole;
import com.GuruDev.ExamPortal.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public User createUser(@RequestBody User user) throws Exception {

        Set<UserRole> userRoles = new HashSet<>();

        Role role  = new Role();
        role.setRoleId(1L);
        role.setRoleName("NORMAL");

        UserRole userRole = new UserRole();
        userRole.setRole(role);
        userRole.setUser(user);

        userRoles.add(userRole);

        return this.userService.createUser(user,userRoles);

    }

    @GetMapping("/{userName}")
    public User getUser(@PathVariable("userName") String name){
        return this.userService.getUser(name);
    }
}
