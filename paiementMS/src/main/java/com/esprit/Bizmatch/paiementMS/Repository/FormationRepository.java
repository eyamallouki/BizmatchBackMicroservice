package com.esprit.Bizmatch.paiementMS.Repository;

import com.esprit.Bizmatch.paiementMS.Entity.Formation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FormationRepository extends JpaRepository<Formation, Integer> {
    @Query("SELECT MAX(f.idFormation) FROM Formation f")
    Long findLastInsertedId();

}
