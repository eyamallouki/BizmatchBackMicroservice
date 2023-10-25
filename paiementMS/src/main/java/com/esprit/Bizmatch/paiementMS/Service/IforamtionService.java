package com.esprit.Bizmatch.paiementMS.Service;

import com.esprit.Bizmatch.paiementMS.Entity.Formation;

import java.util.List;

public interface IforamtionService {
    Formation AddFormation(Formation formation);
    List<Formation> findAll();


}
