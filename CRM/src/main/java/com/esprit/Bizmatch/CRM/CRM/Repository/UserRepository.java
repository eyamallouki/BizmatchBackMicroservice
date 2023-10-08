package com.esprit.Bizmatch.CRM.CRM.Repository;

import com.esprit.Bizmatch.CRM.CRM.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}