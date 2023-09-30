package com.esprit.Bizmatch.User.BizmatchUserAuthentification.Controller;

import com.esprit.Bizmatch.User.BizmatchUserAuthentification.Service.UserService;
import com.esprit.Bizmatch.User.BizmatchUserAuthentification.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
/*
    @PostMapping({"/registerNewUser"})
    public ResponseEntity<String> registerNewUser(@RequestBody User user) {

        try {
            userService.registerNewUser(user);
            return ResponseEntity.ok("Compte créé avec succès.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
*/
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
