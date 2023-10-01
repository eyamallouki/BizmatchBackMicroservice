package com.esprit.Bizmatch.Register.Register.Controller;

import com.esprit.Bizmatch.Register.Register.Service.*;
import com.esprit.Bizmatch.Register.Register.entity.*;
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

    @Autowired
    private EmailServiceImpl emailServ ;

    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }

    @PostMapping({"/registerNewUser"})
    public User registerNewUser(@RequestBody User user) {

        User savedUser = userService.registerNewUser(user);
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

    //activate compte
    @PutMapping("/activate/{verificationToken}")
    public ResponseEntity activateAccount(@PathVariable String verificationToken) {
        User user = userService.activateUser(verificationToken);
        if (user != null) {
            String to = user.getUserEmail();
            String subject = "Account Created";
            String text = "Your account has been created successfully.";
            emailServ.sendEmail(to, subject, text);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping({"/sms/{userName}"})
    public void SMS(@PathVariable String userName) {
        userService.sms(userName);
    }
}
