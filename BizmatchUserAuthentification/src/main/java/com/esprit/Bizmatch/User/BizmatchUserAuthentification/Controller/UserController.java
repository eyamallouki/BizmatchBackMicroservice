package com.esprit.Bizmatch.User.BizmatchUserAuthentification.Controller;

import com.esprit.Bizmatch.User.BizmatchUserAuthentification.Repository.UserDao;
import com.esprit.Bizmatch.User.BizmatchUserAuthentification.Service.EmailServiceImpl;
import com.esprit.Bizmatch.User.BizmatchUserAuthentification.Service.UserService;
import com.esprit.Bizmatch.User.BizmatchUserAuthentification.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailServiceImpl emailServ;

    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }

    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin() {
        return "This URL is only accessible to the admin";
    }

    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('User')")
    public String forUser() {
        return "This URL is only accessible to the user";
    }

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
