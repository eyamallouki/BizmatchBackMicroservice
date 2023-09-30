package com.esprit.Bizmatch.Register.Register.Controller;

import com.esprit.Bizmatch.Register.Register.Service.*;
import com.esprit.Bizmatch.Register.Register.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }

    @PostMapping({"/registerNewUser"})
    public User registerNewUser(@RequestBody User user) {

        User savedUser = userService.registerNewUser(user);
        //VerificationToken verificationToken = verificationTokenService.createVerificationToken(user); // création du jeton de vérification
       // verificationTokenService.saveVerificationToken(verificationToken);
        return savedUser;
    }

    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "This URL is only accessible to the admin";
    }

    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('User')")
    public String forUser(){
        return "This URL is only accessible to the user";
    }
}
