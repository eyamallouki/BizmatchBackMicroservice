package com.esprit.Bizmatch.suivi.des.objectifsMS.services;

import com.esprit.Bizmatch.suivi.des.objectifsMS.entities.Objectif;

import java.time.LocalDate;
import java.util.List;

public interface IobjectifService {
    Objectif addAndAssignObjectifToUser(Objectif objectif);

    //Objectif updateObjectif(Objectif objectif, Integer idObjectif);

    void deleteObjectif(Integer idObjectif);

    List<Objectif> findByUserName(String userName);

    List<Objectif> findRealisedObjectifsForUserBetweenDates(String userName, LocalDate startDate, LocalDate endDate);

    Integer nbrObjectifDone (String userName);
    Integer nbrObjectifInprogress (String userName);
    Integer nbrObjectifTotaleParuser (String userName);
    List<String> objectisNotDone(String userName);
}