package com.esprit.Bizmatch.partenariatMS.services.Interface;
import com.esprit.Bizmatch.partenariatMS.persistence.entity.DemandeAchat;

import java.util.List;
import java.util.Optional;

public interface DemandeAchatService{
    DemandeAchat addDemandeAchat(DemandeAchat demandeAchat);

    public void update(DemandeAchat demandeAchat);
    public DemandeAchat findById(Integer id);

    Optional<DemandeAchat> findById2(Integer id);

    void deleteDemande(Integer id);

    List<DemandeAchat> findAll();



}
