package com.esprit.Bizmatch.partenariatMS.repositories;

import com.esprit.Bizmatch.partenariatMS.persistence.entity.Recruteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruteurRepository extends JpaRepository<Recruteur, Integer> {
}