package com.esprit.Bizmatch.paiementMS.Repository;

import com.esprit.Bizmatch.paiementMS.Entity.ImageForm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<ImageForm, Integer> {
    Optional<ImageForm> findByName (String name);
    Optional<ImageForm>findByFormation_IdFormation(Integer idFormation);
}
