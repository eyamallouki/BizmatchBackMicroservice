package com.Bizmatch.Expansion.DecouvertMarche.repository;

import com.Bizmatch.Expansion.DecouvertMarche.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}