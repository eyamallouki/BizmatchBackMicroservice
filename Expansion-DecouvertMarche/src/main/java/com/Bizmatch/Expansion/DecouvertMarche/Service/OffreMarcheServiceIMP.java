package com.Bizmatch.Expansion.DecouvertMarche.Service;

import com.Bizmatch.Expansion.DecouvertMarche.Entity.OffreMarche;
import com.Bizmatch.Expansion.DecouvertMarche.repository.OffreMarcheRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OffreMarcheServiceIMP implements IOffreMarcheService {
    private final OffreMarcheRepository offreMarcheRepository;


    @Override
    public OffreMarche createOffre(OffreMarche offreMarche) {
        return offreMarcheRepository.save(offreMarche);
    }

    @Override
    public List<OffreMarche> getAllOffres() {
        return offreMarcheRepository.findAll();
    }

    @Override
    public OffreMarche getOffreById(Integer idoffre) {
        return offreMarcheRepository.findById(idoffre).orElse(null);
    }

    @Override
    public OffreMarche updateOffre(Integer idoffre, OffreMarche offreMarche) {
        OffreMarche existingOffre = getOffreById(idoffre);
        if (existingOffre != null) {
            existingOffre.setLocation(offreMarche.getLocation());
            existingOffre.setNomOffre(offreMarche.getNomOffre());
            existingOffre.setDetailsOffre(offreMarche.getDetailsOffre());
            existingOffre.setCapacite(offreMarche.getCapacite());
            return offreMarcheRepository.save(existingOffre);
        }
        return null;
    }

    @Override
    public void deleteOffre(Integer idoffre) {
        offreMarcheRepository.deleteById(idoffre);
    }

    @Override
    public List<OffreMarche> findByLocation(String location) {
        return offreMarcheRepository.findByLocation(location);
    }
}
