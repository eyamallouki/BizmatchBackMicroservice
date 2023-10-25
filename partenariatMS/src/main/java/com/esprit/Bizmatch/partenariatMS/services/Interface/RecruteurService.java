package com.esprit.Bizmatch.partenariatMS.services.Interface;

import com.esprit.Bizmatch.partenariatMS.persistence.entity.Recruteur;

import java.util.List;
import java.util.Optional;

public interface RecruteurService {

    Recruteur addRecruteur(Recruteur recruteur);

    public void update(Recruteur recruteur);
    public Recruteur findById(Integer idR);

    Optional<Recruteur> findById2(Integer idR);

    void deleteRecruteur(Integer id);

    List<Recruteur> findAll();
}
