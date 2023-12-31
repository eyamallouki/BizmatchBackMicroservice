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

import javax.transaction.Transactional;
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
    @Autowired
    private EmailServiceImpl emailServ;


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
        Role EntrepriseRole = new Role();
        EntrepriseRole.setRoleName("Entreprise");
        EntrepriseRole.setRoleDescription("Entreprise role");
        roleDao.save(EntrepriseRole);

        Role FournisseurRole = new Role();
        FournisseurRole .setRoleName("Fournisseur");
        FournisseurRole .setRoleDescription("Fournisseur Role ");
        roleDao.save(FournisseurRole );




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

    public User registerNewUser(User user) {
        Role role = roleDao.findById("User").get();
        Set<Role> userRoles = new HashSet<>();
        user.setIsverified(0);
        emailServ.sendVerificationEmail(user);
        user.setUserPassword(getEncodedPassword(user.getUserPassword()));
        userRoles.add(role);
        user.setRole(userRoles);

        return userDao.save(user);
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public List<User> getAll(){
        return (List<User>) userDao.findAll();
    }
    public User findOne(String userName){
        return userDao.findById(userName).orElse(null);
    }
    public void delete(String userName){
        User u= userDao.findById(userName).orElse(null);
        u.getRole().clear();
        userDao.delete(u);
    }
    public void update(User user){
        userDao.save(user);
    }
    public void addRoleToUser(String roleName, String userName)
    {
        Role r = roleDao.findById(roleName).orElse(null);
        User u= userDao.findById(userName).orElse(null);

        Set<Role> newRole=new HashSet<Role>();
        newRole.add(r);

        u.setRole(newRole);
        userDao.save(u);
    }

    public long count(){
        long count=userDao.count();
        return count;
    }
    public long countEntreprise(){
        long countEntreprise=0;
        List<User> users= (List<User>) userDao.findAll();
        for(User user:users) {

            Set<Role> roles=user.getRole();
            Role role= roles.iterator().next();
            String rolename = role.getRoleName();
            if(rolename.equals("Entreprise")){
                countEntreprise+=1;
            }
        }
        return countEntreprise;
    }

    //Fournisseur :
    public long countFournisseur(){
        long countFournisseur=0;
        List<User> users= (List<User>) userDao.findAll();
        for(User user:users) {

            Set<Role> roles=user.getRole();
            Role role= roles.iterator().next();
            String rolename = role.getRoleName();
            if(rolename.equals("Fournisseur")){
                countFournisseur+=1;
            }
        }
        return countFournisseur;
    }
    public long countadmin(){
        long countadmin=0;
        List<User> users= (List<User>) userDao.findAll();
        for(User user:users) {

            Set<Role> roles=user.getRole();
            Role role= roles.iterator().next();
            String rolename = role.getRoleName();
            if(rolename.equals("Admin")){
                countadmin+=1;
            }
        }
        return countadmin;
    }
    public long countusers(){
        long countusers=0;
        List<User> users= (List<User>) userDao.findAll();
        for(User user:users) {

            Set<Role> roles=user.getRole();
            Role role= roles.iterator().next();
            String rolename = role.getRoleName();
            if(rolename.equals("User")){
                countusers +=1;
            }
        }
        return countusers;
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
                "This is the ship that made the Kessel Run in fourteen parsecs?").create();

        System.out.println(message.getSid());
    }

    // Reset Password:
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

    public void editUser(User user){
        this.userDao.save(user);
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


    public User retrieve(String username) {
        try{
            return  userDao.findById(username).get();
        } catch (Exception err) {
            System.out.println("Un erreur est survenue : " + err);
        }
        return null;
    }
}
