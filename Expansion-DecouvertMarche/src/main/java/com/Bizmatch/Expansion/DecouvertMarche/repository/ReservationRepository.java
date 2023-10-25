package com.Bizmatch.Expansion.DecouvertMarche.repository;

import com.Bizmatch.Expansion.DecouvertMarche.Entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
}