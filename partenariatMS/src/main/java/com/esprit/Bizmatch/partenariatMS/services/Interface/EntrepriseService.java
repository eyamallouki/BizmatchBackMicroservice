package com.esprit.Bizmatch.partenariatMS.services.Interface;

import com.esprit.Bizmatch.partenariatMS.persistence.entity.DemandeAchat;
import com.esprit.Bizmatch.partenariatMS.persistence.entity.Entreprise;
import com.esprit.Bizmatch.partenariatMS.persistence.entity.Recruteur;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EntrepriseService extends CrudService<Entreprise, Integer>{

    List<Entreprise> findAll();

    Entreprise addEntreprise(String nom, MultipartFile image, String adresse, String details, Double budget, String domaine, DemandeAchat demandeAchat, Recruteur recruteur);

    String notif(DemandeAchat demande, Entreprise entreprise);

    Entreprise trouverMeilleurMatch(DemandeAchat demande);

    String accepterMatch(Entreprise match);
}
