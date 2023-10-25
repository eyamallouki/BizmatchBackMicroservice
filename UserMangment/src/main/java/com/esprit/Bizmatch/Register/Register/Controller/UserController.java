package com.esprit.Bizmatch.Register.Register.Controller;

import com.esprit.Bizmatch.Register.Register.Repository.UserDao;
import com.esprit.Bizmatch.Register.Register.Service.*;
import com.esprit.Bizmatch.Register.Register.entity.*;
import com.esprit.Bizmatch.Register.Register.util.UserCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.List;

@RestController

@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;
    @Autowired
    private EmailServiceImpl emailServ;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private VerificationTokenService verificationTokenService;

    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }

    @PostMapping({"/registerNewUser"})
    public User registerNewUser(@Valid @RequestBody User user) {

        User savedUser = userService.registerNewUser(user);
        VerificationToken verificationToken = verificationTokenService.createVerificationToken(user); // création du jeton de vérification
        verificationTokenService.saveVerificationToken(verificationToken);
        return savedUser;
    }

    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin() {
        return "This URL is only accessible to the admin";
    }

    @GetMapping({"/getallUser"})
   // @PreAuthorize("hasRole('Admin')")
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping({"/user/{userName}"})
    public User findOne(@PathVariable String userName) {
        return userService.findOne(userName);
    }

    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('User')")
    public String forUser() {
        return "This URL is only accessible to the user";
    }

    @DeleteMapping({"/delete/{userName}"})
    @PreAuthorize("hasRole('Admin')")
    public void delete(@PathVariable String userName) {
        userService.delete(userName);
    }

    @PutMapping({"/update"})
    @PreAuthorize("hasRole('User')")
    public void update(@Valid @RequestBody User user) {
        userService.update(user);
    }

    @GetMapping("/count")
    @PreAuthorize("hasRole('Admin')")
    public long count() {
        return userService.count();
    }

    @GetMapping("/countentreprise")
   // @PreAuthorize("hasRole('Admin')")
    public long countEntreprise() {
        return userService.countEntreprise();
    }

    @GetMapping("/countFournisseur")
    public long countFournisseur() {
        return userService.countFournisseur();
    }

    @GetMapping("/countadmin")
   // @PreAuthorize("hasRole('Admin')")
    public long countadmin() {
        return userService.countadmin();
    }

    @GetMapping("/userss")
   // @PreAuthorize("hasRole('Admin')")
    public long countusers() {
        return userService.countusers();
    }

    @GetMapping({"/sms/{userName}"})
    //@PreAuthorize("hasRole('Admin')")
    public void SMS(@PathVariable String userName) {
        userService.sms(userName);
    }

    @PutMapping({"/addroles/{roleName}/{userName}"})
    //@PreAuthorize("hasRole('Admin')")
    public void addRoleToUser(@PathVariable String roleName, @PathVariable String userName) {
        userService.addRoleToUser(roleName, userName);
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

    // http://localhost:8080/checkEmail
    @PostMapping("/checkEmail")
    public UserAccountResponse resetPasswordEmail(@RequestBody UserResetPassword resetPassword) {
        User user = this.userService.findByUserEmail(resetPassword.getEmail());
        UserAccountResponse accountResponse = new UserAccountResponse();
        if (user != null) {
            String code = UserCode.getCode();
            System.out.println("le code est" + code);
            UserMail mail = new UserMail(resetPassword.getEmail(), code);
            System.out.println("le mail est" + resetPassword.getEmail());
            System.out.println("la variable mail est" + mail);
            emailServ.sendCodeByMail(mail);
            System.out.println("la variable User est" + user);
            user.setUserCode(code);
            userDao.save(user);
            accountResponse.setResult("Utulisateur Trouvé ");
        } else {
            accountResponse.setResult("Échec utulisateurs n'exist pas ");
        }
        return accountResponse;
    }

    // http://localhost:8080/resetPassword
    @PostMapping("/resetPassword")
    public UserAccountResponse resetPassword(@RequestBody UserNewPassword newPassword) {
        User user = this.userService.findByUserEmail(newPassword.getEmail());
        UserAccountResponse accountResponse = new UserAccountResponse();
        if (user != null) {
            if (user.getUserCode().equals(newPassword.getCode())) {
                user.setUserPassword(passwordEncoder.encode(newPassword.getPassword()));
                userDao.save(user);
                accountResponse.setResult("Mot de passe réinitialisé avec succès");
            } else {
                accountResponse.setResult("Échec de la réinitialisation du mot de passe");
            }
        } else {
            accountResponse.setResult("Échec de la réinitialisation du mot de passe");
        }
        return accountResponse;
    }

}


