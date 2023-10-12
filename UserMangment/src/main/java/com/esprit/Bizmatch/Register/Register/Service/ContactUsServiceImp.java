package com.esprit.Bizmatch.Register.Register.Service;


import com.esprit.Bizmatch.Register.Register.Repository.ContactUsRepository;
import com.esprit.Bizmatch.Register.Register.entity.AnalyseSentiment;
import com.esprit.Bizmatch.Register.Register.entity.ContactUs;
import com.esprit.Bizmatch.Register.Register.entity.PourcentageSentiment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


@RequiredArgsConstructor
@Service
public class ContactUsServiceImp implements IContactUsService {

    private  final ContactUsRepository contactUsRepository ;

    public ContactUs createContactUs(ContactUs contactUs) {
        return contactUsRepository.save(contactUs);
    }

    public List<ContactUs> getAllContactUs() {
        return contactUsRepository.findAll();
    }

    public ContactUs getContactUsById(Long id) {
        return contactUsRepository.findById(id).orElse(null);
    }

    public ContactUs updateContactUs(Long id, ContactUs updatedContactUs) {
        ContactUs existingContactUs = contactUsRepository.findById(id).orElse(null);
        if (existingContactUs != null) {
            updatedContactUs.setIdContact(id);
            return contactUsRepository.save(updatedContactUs);
        } else {
            return null;
        }
    }

    public boolean deleteContactUs(Long id) {
        if (contactUsRepository.existsById(id)) {
            contactUsRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
    public ContactUs getContactUsByFirstnameAndLastname(String firstname, String lastname) {
        return contactUsRepository.findByFirstNameAndLastName(firstname, lastname);
    }

    private List<String> motsPositifs = Arrays.asList("positif1", "positif2", "positif3");
    private List<String> motsNegatifs = Arrays.asList("négatif1", "négatif2", "négatif3");

    @Override
    public AnalyseSentiment analyserContenu(ContactUs contactUs) {
        String contenu = contactUs.getContenu().toLowerCase();
        int motsPositifsCount = 0;
        int motsNegatifsCount = 0;

        for (String mot : contenu.split("\\s+")) {
            if (motsPositifs.contains(mot)) {
                motsPositifsCount++;
            }
            if (motsNegatifs.contains(mot)) {
                motsNegatifsCount++;
            }
        }

        // Débogage - Afficher les résultats
        System.out.println("Mots positifs : " + motsPositifsCount);
        System.out.println("Mots négatifs : " + motsNegatifsCount);

        return new AnalyseSentiment(motsPositifsCount, motsNegatifsCount);
    }

    public AnalyseSentiment analyserContenuParId(long idContact) {
        // Récupérer la demande de contact par ID (utilisez votre repository)
        ContactUs contactUs = contactUsRepository.findById(idContact).orElse(null);

        if (contactUs != null) {
            // Appeler la méthode d'analyse de contenu existante
            return analyserContenu(contactUs);
        } else {
            return null; // Ou renvoyer une erreur appropriée
        }
    }



    @Override
    public PourcentageSentiment calculerPourcentageSentiment1(ContactUs contactUs) {
        AnalyseSentiment analyseSentiment = analyserContenu(contactUs);
        int totalMots = analyseSentiment.getMotsPositifs() + analyseSentiment.getMotsNegatifs();

        double pourcentagePositif = (double) analyseSentiment.getMotsPositifs() / totalMots * 100;
        double pourcentageNegatif = (double) analyseSentiment.getMotsNegatifs() / totalMots * 100;

        return new PourcentageSentiment(pourcentagePositif, pourcentageNegatif);
    }

    public PourcentageSentiment calculerPourcentageSentimentGlobal() {
        List<ContactUs> contactUsList = contactUsRepository.findAll(); // Récupérer toutes les demandes de contact

        int totalMotsPositifs = 0;
        int totalMotsNegatifs = 0;

        // Parcourir toutes les demandes de contact pour calculer les totaux
        for (ContactUs contactUs : contactUsList) {
            AnalyseSentiment analyseSentiment = analyserContenu(contactUs);
            totalMotsPositifs += analyseSentiment.getMotsPositifs();
            totalMotsNegatifs += analyseSentiment.getMotsNegatifs();
        }

        int totalMots = totalMotsPositifs + totalMotsNegatifs;
        double pourcentagePositif = (double) totalMotsPositifs / totalMots * 100;
        double pourcentageNegatif = (double) totalMotsNegatifs / totalMots * 100;

        return new PourcentageSentiment(pourcentagePositif, pourcentageNegatif);
    }

}
