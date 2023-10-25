package com.esprit.Bizmatch.paiementMS.Repository;

import com.esprit.Bizmatch.paiementMS.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
}