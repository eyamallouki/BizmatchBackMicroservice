package com.esprit.Bizmatch.partenariatMS.services.Implementation;

import com.esprit.Bizmatch.partenariatMS.persistence.entity.Recruteur;
import com.esprit.Bizmatch.partenariatMS.repositories.RecruteurRepository;
import com.esprit.Bizmatch.partenariatMS.services.Interface.RecruteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecruteurServiceIMP implements RecruteurService {
    @Autowired
    RecruteurRepository recruteurRepository;

    @Override
    public Recruteur addRecruteur(Recruteur recruteur) {
        return recruteurRepository.save(recruteur);
    }

    @Override
    public void update(Recruteur recruteur) {
        recruteurRepository.save(recruteur);
    }


    @Override
    public Recruteur findById(Integer idR) {
        return recruteurRepository.findById(idR).orElse(null);
    }
    @Override
    public Optional<Recruteur> findById2(Integer idR) {
        return recruteurRepository.findById(idR);
    }
    @Override
    public void deleteRecruteur(Integer idR) {
        recruteurRepository.deleteById(idR);
    }
    @Override
    public List<Recruteur> findAll() {
        return recruteurRepository.findAll();
    }
}
