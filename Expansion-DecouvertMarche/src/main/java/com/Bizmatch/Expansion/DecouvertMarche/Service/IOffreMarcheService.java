package com.Bizmatch.Expansion.DecouvertMarche.Service;

import com.Bizmatch.Expansion.DecouvertMarche.Entity.OffreMarche;

import java.util.List;

public interface IOffreMarcheService {


    public OffreMarche createOffre(OffreMarche offreMarche);

    public List<OffreMarche> getAllOffres();

    public OffreMarche getOffreById(Integer idoffre);

    public OffreMarche updateOffre(Integer idoffre, OffreMarche offreMarche);

    public void deleteOffre(Integer idoffre);

    public List<OffreMarche> findByLocation(String location);
}
