package com.Bizmatch.PrestationServiceMS.Services;

import com.Bizmatch.PrestationServiceMS.Entity.FournisseurOffer;

import java.util.List;

public interface IFournisseurService {

    public FournisseurOffer addFournisseur(FournisseurOffer fournisseurOffer);
    FournisseurOffer getFournisseurById(Integer id);
    FournisseurOffer getFournisseurByUsername(String username);
    List<FournisseurOffer> getAllFournisseurs();
    FournisseurOffer updateFournisseur(FournisseurOffer fournisseurOffer);
    void deleteFournisseurById(Integer id);
    void deleteFournisseurByUsername(String username);

    public FournisseurOffer assignUserToFournisseur(Integer idfournisseur, String Username) ;


        public List<FournisseurOffer> searchFournisseursByDomaine(String domaine);

    public List<FournisseurOffer> sortFournisseursByAssignedUsers();

    public Integer getUserCount(Integer fournisseurId);
}
