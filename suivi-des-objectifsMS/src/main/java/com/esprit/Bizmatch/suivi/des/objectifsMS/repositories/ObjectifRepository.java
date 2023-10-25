package com.esprit.Bizmatch.suivi.des.objectifsMS.repositories;

import com.esprit.Bizmatch.suivi.des.objectifsMS.entities.Objectif;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ObjectifRepository extends JpaRepository<Objectif, Integer> {

    @Query("SELECT o FROM Objectif o WHERE o.userName = :userName AND o.dateRealisation BETWEEN :startDate AND :endDate")
    List<Objectif> findRealisedObjectifsForUserBetweenDates(
            @Param("userName") String userName,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
    List<Objectif> findByUserName( String userName);
    @Query("DELETE FROM Objectif o WHERE o.userName = :userName")
    void deleteObjectifByUserName(String userName);

    @Query("SELECT count(o)  from Objectif o WHERE o.userName = :userName AND o.status = 'Done' ")
    Integer nbrObjectifDone(@Param("userName") String userName);

    @Query("SELECT count(o)  from Objectif o WHERE o.userName = :userName AND o.status = 'InProgress' ")
    Integer nbrObjectifInprogress(@Param("userName") String userName);
    @Query("SELECT count(o) from Objectif  o WHERE o.userName = :userName")
    Integer nbrObjectifTotaleParuser(@Param("userName") String userName);

    @Query("SELECT o.title FROM Objectif o WHERE o.userName = :userName AND o.status != 'Done'  AND o.endDate < CURRENT_DATE")
    List<String> objectisNotDone(@Param("userName") String userName);
}
