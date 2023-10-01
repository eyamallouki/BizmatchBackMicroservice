package com.esprit.Bizmatch.User.BizmatchUserAuthentification.Service;

import com.esprit.Bizmatch.User.BizmatchUserAuthentification.Repository.RoleDao;
import com.esprit.Bizmatch.User.BizmatchUserAuthentification.Repository.UserDao;
import com.esprit.Bizmatch.User.BizmatchUserAuthentification.entity.Role;
import com.esprit.Bizmatch.User.BizmatchUserAuthentification.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

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
/*
   public void registerNewUser(User user) {
            Role role = roleDao.findById("User").get();
            Set<Role> userRoles = new HashSet<>();
            userRoles.add(role);

            user.setRole(userRoles);
            user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));

            userDao.save(user);
    }*/


    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public boolean ifEmailExist(String UserEmail){
        return userDao.existsByUserEmail(UserEmail);
    }

    @Transactional
    public String getPasswordByUserEmail(String userEmail){
        return userDao.getPasswordByUserEmail(userEmail);
    }

    public User findByUserEmail(String UserEmail)
    {
        return this.userDao.findByUserEmail(UserEmail);
    }
}
