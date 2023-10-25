package com.esprit.Bizmatch.partenariatMS.repositories;


import com.esprit.Bizmatch.partenariatMS.persistence.entity.DemandeAchat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface DemandeAchatReporitory extends JpaRepository<DemandeAchat, Integer> {
    @Query("SELECT MAX(d.id) FROM DemandeAchat d")
    Long findLastInsertedId();


}