package com.Bizmatch.Expansion.DecouvertMarche.repository;

import com.Bizmatch.Expansion.DecouvertMarche.Entity.OffreMarche;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OffreMarcheRepository extends JpaRepository<OffreMarche, Integer> {
    List<OffreMarche> findByLocation(String location);
}