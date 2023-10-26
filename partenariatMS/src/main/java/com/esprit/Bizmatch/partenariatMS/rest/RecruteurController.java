package com.esprit.Bizmatch.partenariatMS.rest;

import com.esprit.Bizmatch.partenariatMS.persistence.entity.Recruteur;
import com.esprit.Bizmatch.partenariatMS.services.Interface.RecruteurService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/recruteur")
@RequiredArgsConstructor
public class RecruteurController {
    private final RecruteurService recruteurService;

    @PutMapping("/update/{idR}")
    public ResponseEntity<Void> updateRecruteur(@PathVariable Integer idR, @RequestBody Recruteur updateRecruteur) {
        Recruteur existingRecruteur = recruteurService.findById(idR);

        if (existingRecruteur != null) {
            existingRecruteur.setNom(updateRecruteur.getNom());
            existingRecruteur.setEmail(updateRecruteur.getEmail());
            existingRecruteur.setPrenom(updateRecruteur.getPrenom());
            existingRecruteur.setDure(updateRecruteur.getDure());
            existingRecruteur.setTelephone(updateRecruteur.getTelephone());
            existingRecruteur.setTypeRec(updateRecruteur.getTypeRec());
            // Mettez à jour d'autres champs au besoin

            recruteurService.update(existingRecruteur);
        }
        return ResponseEntity.ok().build();

    }

    @PostMapping("/add")
    public Recruteur addRecruteur(@RequestBody Recruteur recruteur) {
        return recruteurService.addRecruteur(recruteur);
    }

    @GetMapping("/getAll")
    public List<Recruteur> getAllRecruteurs() {
        return recruteurService.findAll();
    }

    @DeleteMapping("/delete/{idR}")
    public void deleteRecruteur(@PathVariable Integer idR) {
        recruteurService.deleteRecruteur(idR);
    }

    @GetMapping("/get/{idR}")
    public Optional<Recruteur> getRecruteurById(@PathVariable Integer idR) {
       return recruteurService.findById2(idR);
    }

}