package com.esprit.Bizmatch.User.BizmatchUserAuthentification.Repository;

import com.esprit.Bizmatch.User.BizmatchUserAuthentification.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserDao extends CrudRepository<User, String> {
    @Query("SELECT u FROM User u WHERE u.userName = :username")
    User findByUsername(String username);
}
