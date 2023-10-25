package com.Bizmatch.PrestationServiceMS.Repository;

import com.Bizmatch.PrestationServiceMS.Entity.FournisseurOffer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FournisseurRepository extends JpaRepository<FournisseurOffer, Integer> {

    FournisseurOffer findByUsername(String username);
    void deleteByUsername(String username);

    List<FournisseurOffer> findByDomainesactiviteContainingIgnoreCase(String domaine);
}