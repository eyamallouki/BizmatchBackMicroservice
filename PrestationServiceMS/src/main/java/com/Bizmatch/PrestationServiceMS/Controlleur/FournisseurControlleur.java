package com.Bizmatch.PrestationServiceMS.Controlleur;

import com.Bizmatch.PrestationServiceMS.Entity.FournisseurOffer;
import com.Bizmatch.PrestationServiceMS.Services.IFournisseurService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Fournisseur")
public class FournisseurControlleur {

    @Autowired
     IFournisseurService fournisseurService ;

    @PostMapping("/addFour")
    public FournisseurOffer addFournisseur(@RequestBody FournisseurOffer fournisseurOffer){
        return fournisseurService.addFournisseur(fournisseurOffer);    }



    @GetMapping("/getByUsername/{username}")
    public FournisseurOffer getFournisseurByUsername(@PathVariable String username) {
        return fournisseurService.getFournisseurByUsername(username);
    }

    @GetMapping("/getAllFour")
    public List<FournisseurOffer> getAllFournisseurs() {
        return fournisseurService.getAllFournisseurs();
    }

    @PutMapping("/updateFour")
    public FournisseurOffer updateFournisseur(@RequestBody FournisseurOffer fournisseurOffer) {
        return fournisseurService.updateFournisseur(fournisseurOffer);
    }



    @DeleteMapping("/deleteByUsername/{username}")
    public void deleteFournisseurByUsername(@PathVariable String username) {
        fournisseurService.deleteFournisseurByUsername(username);
    }

    @PutMapping("/assignUserToFournisseur")
    public FournisseurOffer assignUserToFournisseur(@RequestParam Integer idfournisseur, @RequestParam String userUsername) {
        return fournisseurService.assignUserToFournisseur(idfournisseur, userUsername);
    }




    @GetMapping("/searchFournisseursByDomaine")
    public List<FournisseurOffer> searchFournisseursByDomaine(@RequestParam String domaine) {
        return fournisseurService.searchFournisseursByDomaine(domaine);
    }

    @GetMapping("/sortFournisseursByAssignedUsers")
    public List<FournisseurOffer> sortFournisseursByAssignedUsers() {
        return fournisseurService.sortFournisseursByAssignedUsers();
    }

    @GetMapping("/{fournisseurId}/f")
    public ResponseEntity<Integer> getUserCount(@PathVariable Integer fournisseurId) {
        Integer userCount = fournisseurService.getUserCount(fournisseurId);

        if (userCount != null) {
            return ResponseEntity.ok(userCount);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
