package com.esprit.Bizmatch.suivi.des.objectifsMS.services;

import com.esprit.Bizmatch.suivi.des.objectifsMS.entities.Objectif;
import com.esprit.Bizmatch.suivi.des.objectifsMS.repositories.ObjectifRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ObjectifServiceImp implements IobjectifService {
    private final ObjectifRepository objectifRepository;
    @Override
    public Objectif addAndAssignObjectifToUser(Objectif objectif) {
        return this.objectifRepository.save(objectif);
    }


    @Override
    public void deleteObjectif(Integer idObjectif) {
        this.objectifRepository.deleteById(idObjectif);
    }

    @Override
    public List<Objectif> findByUserName(String userName) {
        return this.objectifRepository.findByUserName(userName);
    }

   @Override
    public List<Objectif> findRealisedObjectifsForUserBetweenDates(String userName, LocalDate startDate, LocalDate endDate) {
        return objectifRepository.findRealisedObjectifsForUserBetweenDates(userName, startDate, endDate);

    }

    @Override
    public Integer nbrObjectifDone(String userName) {
        return this.objectifRepository.nbrObjectifDone(userName);
    }

    @Override
    public Integer nbrObjectifInprogress(String userName) {
        return this.objectifRepository.nbrObjectifInprogress(userName);
    }

    @Override
    public Integer nbrObjectifTotaleParuser(String userName) {
        return this.objectifRepository.nbrObjectifTotaleParuser(userName);
    }
    @Override
    public List<String> objectisNotDone(String userName) {
        return this.objectifRepository.objectisNotDone(userName);
    }


}
