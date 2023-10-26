package com.esprit.Bizmatch.Forum.Repo;


import com.esprit.Bizmatch.Forum.Entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserDao extends CrudRepository<User, String> {
    @Query("SELECT u FROM User u WHERE u.userName = :username")
    User findByUsername(String username);

    public User findByUserEmail(String UserEmail);

    public boolean existsByUserEmail(String UserEmail);

    @Query("select u.userPassword from User u where u.userEmail=?1")
    public String getPasswordByUserEmail(String UserEmail);

    User findByVerificationToken(String token);
    User findByUserName(String username);
}
