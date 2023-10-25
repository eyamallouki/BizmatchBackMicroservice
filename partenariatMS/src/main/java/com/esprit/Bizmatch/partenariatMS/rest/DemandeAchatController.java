package com.esprit.Bizmatch.partenariatMS.rest;

import com.esprit.Bizmatch.partenariatMS.persistence.entity.DemandeAchat;
import com.esprit.Bizmatch.partenariatMS.repositories.DemandeAchatReporitory;
import com.esprit.Bizmatch.partenariatMS.services.Interface.DemandeAchatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/demandeAchat")
@RequiredArgsConstructor
public class DemandeAchatController {
    private final DemandeAchatService demandeAchatService;
    private final DemandeAchatReporitory demandeAchatReporitory;

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateDemandeAchat(@PathVariable Integer id, @RequestBody DemandeAchat updatedDemandeAchat) {
        DemandeAchat existingDemandeAchat = demandeAchatService.findById(id);

        if (existingDemandeAchat != null) {
            existingDemandeAchat.setNom(updatedDemandeAchat.getNom());
            existingDemandeAchat.setAdresse(updatedDemandeAchat.getAdresse());
            existingDemandeAchat.setBudget(updatedDemandeAchat.getBudget());
            existingDemandeAchat.setDure(updatedDemandeAchat.getDure());
            existingDemandeAchat.setSiteWeb(updatedDemandeAchat.getSiteWeb());
            existingDemandeAchat.setTypePart(updatedDemandeAchat.getTypePart());


            demandeAchatService.update(existingDemandeAchat);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public DemandeAchat addDemandeAchat(@RequestBody DemandeAchat demandeAchat) {
        return demandeAchatService.addDemandeAchat(demandeAchat);
    }

    @GetMapping("/getAll")
    public List<DemandeAchat> getAllDemandes() {
        return demandeAchatService.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteDemande(@PathVariable Integer id) {
        demandeAchatService.deleteDemande(id);
    }

    @GetMapping("/get/{id}")
    public Optional<DemandeAchat> getDemandeById(@PathVariable Integer id) {
        return demandeAchatService.findById2(id);
    }


    @GetMapping("/lastidpost")
    public Long lastidpost(){
        return this.demandeAchatReporitory.findLastInsertedId();
    }


}
