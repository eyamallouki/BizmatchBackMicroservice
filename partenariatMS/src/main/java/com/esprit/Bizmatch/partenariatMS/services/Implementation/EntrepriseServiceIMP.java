package com.esprit.Bizmatch.partenariatMS.services.Implementation;
import com.esprit.Bizmatch.partenariatMS.persistence.entity.DemandeAchat;
import com.esprit.Bizmatch.partenariatMS.persistence.entity.Entreprise;
import com.esprit.Bizmatch.partenariatMS.persistence.entity.Recruteur;
import com.esprit.Bizmatch.partenariatMS.repositories.EntrepriseRepository;
import com.esprit.Bizmatch.partenariatMS.services.Interface.EntrepriseService;
import com.esprit.Bizmatch.partenariatMS.services.Interface.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Scanner;

@Service
public class EntrepriseServiceIMP extends CrudServiceIMP<Entreprise,Integer> implements EntrepriseService {


@Autowired
EntrepriseRepository entrepriseRepository;

@Autowired
    UploadFileService uploadFileService;

@Value("${file.upload}")
private String pathFile;
    @Override
    public List<Entreprise> findAll() {
        return entrepriseRepository.findAll();
    }
@Override
public Entreprise addEntreprise(String nom, MultipartFile image, String adresse, String details, Double budget, String domaine, DemandeAchat demandeAchat, Recruteur recruteur) {

    // Sauvegarde de l'image
    boolean fileAdded = uploadFileService.addFile(image);
    if (!fileAdded) {
        throw new RuntimeException("Erreur lors de la sauvegarde de l'image.");
    }
    String imagePath = pathFile + image.getOriginalFilename();

    // Création de l'objet Entreprise
    Entreprise entreprise = new Entreprise();
    entreprise.setNom(nom);
    entreprise.setAdresse(adresse);
    entreprise.setPhoto(imagePath);
    entreprise.setDetails(details);
    entreprise.setBudget(budget); // Set the budget
    entreprise.setDomaine(domaine); // Set the domaine
 //   entreprise.setDemandeAchat(demandeAchat);
 //   entreprise.setRecruteur(recruteur);

    Entreprise savedEntreprise = entrepriseRepository.save(entreprise);

    return savedEntreprise;
}

    @Override
    public String notif(DemandeAchat demande, Entreprise entreprise) {
        if (demande == null) {
            return "Demande introuvable.";
        }

        if (entreprise == null) {
            return "Aucun meilleur match trouvé.";
        }

        String message = "Notification pour la demande : " + demande.getNom() + ":\n";
        message += "Le meilleur match est l'entreprise: " + entreprise.getNom() + ":\n";
        message += "Detail: " + entreprise.getDetails() + "\n";
        message += "l'entreprise va décider cette demande est accepté ou refusé";
        return message;
    }


    @Override
    public Entreprise trouverMeilleurMatch(DemandeAchat demande) {
        Entreprise meilleurMatch = null;
        double meilleureNote = 0.0;
        List<Entreprise> entreprises = entrepriseRepository.findAll();

        for (Entreprise entreprise : entreprises) {
            double note = 0.0;
            if (demande.getTypePart().equals(entreprise.getDomaine())) {
                note += 1.0;
            }
            if (demande.getBudget().equals(entreprise.getBudget())) {
                note += 1.0;
            }

            if (note > meilleureNote) {
                meilleurMatch = entreprise;
                meilleureNote = note;
            }
        }
        // call the notif method to send the notification
        notif(demande, meilleurMatch);

        return meilleurMatch;
    }
    @Override
    public String accepterMatch(Entreprise match) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Voulez-vous accepter le matching ? (oui ou non)");
        String reponse = scanner.nextLine();

        if ("oui".equalsIgnoreCase(reponse)) {
            match.setAccepte(true);
            entrepriseRepository.save(match);
            return "Matching accepté, votre demande sera préparée aux plus brefs délais.";
        } else {
            match.setAccepte(false);
            entrepriseRepository.save(match);
            return "Matching refusé, nous ferons de notre mieux pour vous trouver le bon matching lors de votre suivante demande .";
        }
    }

        }