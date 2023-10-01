package com.esprit.Bizmatch.Register.Register.Repository;

import com.esprit.Bizmatch.Register.Register.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    VerificationToken findByToken(String Token);
}
