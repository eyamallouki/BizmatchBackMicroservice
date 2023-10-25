package com.esprit.Bizmatch.partenariatMS.rest;

import com.esprit.Bizmatch.partenariatMS.persistence.entity.DemandeAchat;
import com.esprit.Bizmatch.partenariatMS.persistence.entity.Entreprise;
import com.esprit.Bizmatch.partenariatMS.services.Interface.DemandeAchatService;
import com.esprit.Bizmatch.partenariatMS.services.Interface.EntrepriseService;
import com.esprit.Bizmatch.partenariatMS.services.Interface.RecruteurService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;


@RestController
@RequestMapping("/entreprise")
@AllArgsConstructor
public class EntrepriseController{
    private EntrepriseService entrepriseService;
    private DemandeAchatService demandeAchatService;
    private RecruteurService recruteurService;

    @GetMapping
    List<Entreprise> retrieveAll() {
        return entrepriseService.retrieveAll();
    }

    @GetMapping("/getAll")
    public List<Entreprise> getAllEntrprises() {
        return entrepriseService.findAll();
    }

    @PostMapping("/add")
    void add(@Valid @RequestBody Entreprise e) {
    /*    if (Objects.nonNull(e.getDemandeAchat()) && Objects.nonNull(e.getDemandeAchat().getId()) && Objects.nonNull(e.getRecruteur()) && Objects.nonNull(e.getRecruteur().getIdR())){
            DemandeAchat demandeAchat = demandeAchatService.findById(e.getDemandeAchat().getId());
            e.setDemandeAchat(demandeAchat);
            Recruteur recruteur = recruteurService.findById(e.getRecruteur().getIdR());
            e.setRecruteur(recruteur);
        }

     */
        entrepriseService.add(e);
    }

    @PutMapping("/edit")
    void update(@Valid @RequestBody Entreprise e) {
        entrepriseService.update(e);
    }

    @DeleteMapping("/delete/{id}")
    void remove(@PathVariable("id") Integer id) {
        entrepriseService.remove(id);
    }

    @GetMapping("/{id}")
    Entreprise retrieve(@PathVariable("id") Integer id) {
        return entrepriseService.retrieve(id);
    }

    @PostMapping("/addavecImage")
    public ResponseEntity<Entreprise> addEntreprise(
            @RequestParam("nom") String nom,
            @RequestParam("image") MultipartFile image,
            @RequestParam("adresse") String adresse,
            @RequestParam("details") String details,
            @RequestParam("budget") Double budget,
            @RequestParam("domaine") String domaine ) {
           // @RequestParam("recruteurId") Integer recruteurId,
         //   @RequestParam("demandeAchatId") Integer demandeAchatId) {
        // Récupération de la demande
     //   DemandeAchat demandeAchat = demandeAchatService.findById(demandeAchatId);
     //   Recruteur recruteur = recruteurService.findById(recruteurId);
        // Appel du service pour ajouter l'entreprise
        Entreprise entreprise = new Entreprise();
        entreprise.setNom(nom);
        entreprise.setPhoto(image.getOriginalFilename());
        entreprise.setAdresse(adresse);
        entreprise.setDetails(details);
        entreprise.setBudget(budget);
        entreprise.setDomaine(domaine);
      //  entreprise.setDemandeAchat(demandeAchat);
      //  entreprise.setRecruteur(recruteur);

        Entreprise savedEntreprise = entrepriseService.add(entreprise);

        return ResponseEntity.ok(savedEntreprise);
    }

    @GetMapping("/meilleurMatch/{id}")
    public ResponseEntity<String> trouverMeilleurMatch(@PathVariable("id") Integer id) {
        // Retrieve the DemandeAchat object by its ID
        DemandeAchat demande = demandeAchatService.findById(id);
        if (demande == null) {
            return ResponseEntity.notFound().build();
        }
        Entreprise meilleurMatch = entrepriseService.trouverMeilleurMatch(demande);
        String notifMessage = entrepriseService.notif(demande, meilleurMatch);
        return ResponseEntity.ok().body(notifMessage);
    }

    @PostMapping("/accepter")
    public ResponseEntity<String> accepterMatch(@RequestBody Entreprise match) {
        String message = entrepriseService.accepterMatch(match);
        return ResponseEntity.ok(message);
    }

    @PostMapping("/upload-file")
    public String uploadImage(@RequestParam("file") MultipartFile file) throws Exception {
        String Path_Directory = "C:\\Centrale-d-achat-P-I4-me-ModuleOffre\\src\\main\\resources\\static\\downloadFile";
        Files.copy(file.getInputStream(), Paths.get(Path_Directory + File.separator + file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
        return "Successfully Image is Uploaded";
    }
}
