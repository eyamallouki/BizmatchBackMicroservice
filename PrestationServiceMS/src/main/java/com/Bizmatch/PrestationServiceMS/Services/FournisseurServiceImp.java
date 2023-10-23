package com.Bizmatch.PrestationServiceMS.Services;

import com.Bizmatch.PrestationServiceMS.Entity.FournisseurOffer;
import com.Bizmatch.PrestationServiceMS.Entity.Role;
import com.Bizmatch.PrestationServiceMS.Entity.User;
import com.Bizmatch.PrestationServiceMS.Repository.FournisseurRepository;
import com.Bizmatch.PrestationServiceMS.Repository.RoleRepository;
import com.Bizmatch.PrestationServiceMS.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class FournisseurServiceImp implements IFournisseurService {

    private final FournisseurRepository fournisseurRepository ;
    private  final UserRepository userRepository ;

    private  final RoleRepository roleRepository ;
    @Override
    public FournisseurOffer addFournisseur(FournisseurOffer fournisseur) {
        return fournisseurRepository.save(fournisseur);
    }

    @Override
    public FournisseurOffer getFournisseurById(Integer Idfournisseur) {
        return fournisseurRepository.findById(Idfournisseur).orElse(null);
    }

    @Override
    public FournisseurOffer getFournisseurByUsername(String username) {
        return fournisseurRepository.findByUsername(username);
    }

    @Override
    public List<FournisseurOffer> getAllFournisseurs() {
        return fournisseurRepository.findAll();
    }

    @Override
    public FournisseurOffer updateFournisseur(FournisseurOffer fournisseurOffer) {
        if (fournisseurRepository.existsById(fournisseurOffer.getIdfournisseur())) {
            return fournisseurRepository.save(fournisseurOffer);
        }
        return null; // Le fournisseur n'existe pas
    }



    @Override
    public void deleteFournisseurById(Integer Idfournisseur) {
        fournisseurRepository.deleteById(Idfournisseur);
    }

    @Override
    public void deleteFournisseurByUsername(String username) {
        fournisseurRepository.deleteByUsername(username);
    }

    @Override
    public FournisseurOffer assignUserToFournisseur(Integer idfournisseur, String userUsername) {
        FournisseurOffer fournisseurOffer = getFournisseurById(idfournisseur);
        User user = userRepository.findByUsername(userUsername);

        if (fournisseurOffer != null && user != null) {
            fournisseurOffer.getUsersfournisseur().add(user);
            return updateFournisseur(fournisseurOffer);
        } else {
            // Gérer les erreurs si le fournisseur ou l'utilisateur n'existe pas
            return null;
        }
    }



    @Override
    public List<FournisseurOffer> searchFournisseursByDomaine(String domaine) {
        return fournisseurRepository.findByDomainesactiviteContainingIgnoreCase(domaine);
    }

    @Override
    public List<FournisseurOffer> sortFournisseursByAssignedUsers() {
        List<FournisseurOffer> fournisseurOffers = getAllFournisseurs();

        // Tri croissant du nombre d'utilisateurs assignés
        fournisseurOffers.sort(Comparator.comparingInt(f -> f.getUsersfournisseur().size()));

        return fournisseurOffers;
    }

    @Override
    public Integer getUserCount(Integer fournisseurId) {
        Optional<FournisseurOffer> optionalFournisseur = fournisseurRepository.findById(fournisseurId);

        if (optionalFournisseur.isPresent()) {
            FournisseurOffer fournisseur = optionalFournisseur.get();
            int userCount = fournisseur.getUsersfournisseur().size();
            return userCount;
        }

        return null; // Ou renvoyez une valeur par défaut, par exemple -1, si le fournisseur n'est pas trouvé.
    }



}
