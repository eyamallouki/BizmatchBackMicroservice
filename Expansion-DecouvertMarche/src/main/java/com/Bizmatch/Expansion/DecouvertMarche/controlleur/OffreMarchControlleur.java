package com.Bizmatch.Expansion.DecouvertMarche.controlleur;

import com.Bizmatch.Expansion.DecouvertMarche.Entity.OffreMarche;
import com.Bizmatch.Expansion.DecouvertMarche.Service.IOffreMarcheService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/OffreMarche")
@RequiredArgsConstructor
public class OffreMarchControlleur {
    private final IOffreMarcheService offreMarcheService ;

    @PostMapping("/addMarche")
    public ResponseEntity<OffreMarche> createOffre(@RequestBody OffreMarche offreMarche) {
        OffreMarche nouvelleOffre = offreMarcheService.createOffre(offreMarche);
        return ResponseEntity.ok(nouvelleOffre);
    }

    @GetMapping("/listoffre")
    public ResponseEntity<List<OffreMarche>> getAllOffres() {
        List<OffreMarche> offres = offreMarcheService.getAllOffres();
        return ResponseEntity.ok(offres);
    }

    @GetMapping("/listbyId/{idoffre}")
    public ResponseEntity<OffreMarche> getOffreById(@PathVariable Integer idoffre) {
        OffreMarche offre = offreMarcheService.getOffreById(idoffre);
        if (offre != null) {
            return ResponseEntity.ok(offre);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{idoffre}")
    public ResponseEntity<OffreMarche> updateOffre(@PathVariable Integer idoffre, @RequestBody OffreMarche offreMarche) {
        OffreMarche offreMiseAJour = offreMarcheService.updateOffre(idoffre, offreMarche);
        if (offreMiseAJour != null) {
            return ResponseEntity.ok(offreMiseAJour);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{idoffre}")
    public ResponseEntity<Void> deleteOffre(@PathVariable Integer idoffre) {
        offreMarcheService.deleteOffre(idoffre);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/find/byLocation")
    public ResponseEntity<List<OffreMarche>> getOffresByLocation(@RequestParam String location) {
        List<OffreMarche> offres = offreMarcheService.findByLocation(location);
        return ResponseEntity.ok(offres);
    }
}
