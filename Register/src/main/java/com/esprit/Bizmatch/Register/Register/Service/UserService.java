package com.esprit.Bizmatch.Register.Register.Service;

import com.esprit.Bizmatch.Register.Register.Repository.RoleDao;
import com.esprit.Bizmatch.Register.Register.Repository.*;
import com.esprit.Bizmatch.Register.Register.entity.Role;
import com.esprit.Bizmatch.Register.Register.entity.User;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.twilio.type.PhoneNumber;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private EmailServiceImpl emailServ ;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public void initRoleAndUser() {

        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleDao.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default role for newly created record");
        roleDao.save(userRole);

        User adminUser = new User();
        adminUser.setUserName("admin123");
        adminUser.setUserPassword(getEncodedPassword("admin@pass"));
        adminUser.setUserFirstName("admin");
        adminUser.setUserLastName("admin");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userDao.save(adminUser);

//        User user = new User();
//        user.setUserName("raj123");
//        user.setUserPassword(getEncodedPassword("raj@123"));
//        user.setUserFirstName("raj");
//        user.setUserLastName("sharma");
//        Set<Role> userRoles = new HashSet<>();
//        userRoles.add(userRole);
//        user.setRole(userRoles);
//        userDao.save(user);
    }

  /* public User registerNewUser(User user) {
       Role role = roleDao.findById("User").get();
       Set<Role> userRoles = new HashSet<>();
      // user.setIsverified(0);
     //  emailServ.sendVerificationEmail(user);
       user.setUserPassword(getEncodedPassword(user.getUserPassword()));
       userRoles.add(role);
       user.setRole(userRoles);

       return userDao.save(user);
    }*/

    /*
    public User registerNewUser(User user) {
            Role role = roleDao.findById("User").get();
            Set<Role> userRoles = new HashSet<>();
        user.setIsverified(0);
        emailServ.sendVerificationEmail(user);
            userRoles.add(role);

            user.setRole(userRoles);
            user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));

            userDao.save(user);
        return user;
    }*/

    public User registerNewUser(User user) {
        Role role = roleDao.findById("User").get();
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRole(userRoles);
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));

        // Enregistrez d'abord l'utilisateur dans la base de données
        user = userDao.save(user);

        // Ensuite, envoyez l'e-mail de vérification
        user.setIsverified(0); // Marquez l'utilisateur comme non vérifié
        emailServ.sendVerificationEmail(user);

        return user;
    }



    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public User activateUser(String token) {
        User user = userDao.findByVerificationToken(token);
        if (user != null) {
            user.setIsverified(1);
            user.setVerificationToken(null);
            userDao.save(user);
        }
        return user;
    }

    public static final String ACCOUNT_SID = "ACa9b8ab43f7a7e83f03838cc677d4c33b";
    public static final String AUTH_TOKEN = "9206caa708d1cc9c97c1d004501bfb46";

    public static final String sender_number ="+12512209633";

    public void sms(String userName){
        User user = userDao.findById(userName).orElse(null);
        // Find your Account Sid and Token at twilio.com/user/account
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber("+216"+ user.getUserNumber()),
                new PhoneNumber(sender_number),
                "lawti lawti ya eya?").create();

        System.out.println(message.getSid());
    }
}
