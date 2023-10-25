package com.esprit.Bizmatch.suivi.des.objectifsMS.controllers;

import com.esprit.Bizmatch.suivi.des.objectifsMS.entities.Objectif;
import com.esprit.Bizmatch.suivi.des.objectifsMS.repositories.ObjectifRepository;
import com.esprit.Bizmatch.suivi.des.objectifsMS.services.IobjectifService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/suiviObjectif")

public class ObjectifConroller {
    private final IobjectifService iobjectifService;
    private final ObjectifRepository objectifRepository;

    @PostMapping("")
    public ResponseEntity <Objectif> addAndAssignObjectifToUser(@RequestBody Objectif o) throws Exception{
        return ResponseEntity.ok(iobjectifService.addAndAssignObjectifToUser(o));
    }

    @PutMapping("/{idObjectif}")
    public void updateObjectif(@RequestBody Objectif objectif, @PathVariable Integer idObjectif ) {
        Optional<Objectif> objectifToUpdate = objectifRepository.findById(idObjectif);
        if (objectifToUpdate.isPresent()) {
            Objectif existingObjectif = objectifToUpdate.get();

            //  existingObjectif.setDescription(objectif.getDescription());
            //existingObjectif.setTitle(objectif.getTitle());
            //existingObjectif.setPirorite(objectif.getPirorite());
            //existingObjectif.setEndDate(objectif.getEndDate());
            //existingObjectif.setStartDate(objectif.getStartDate());
           // existingObjectif.setDateRealisation(objectif.getDateRealisation());
            existingObjectif.setStatus(objectif.getStatus());

            Objectif updatedObjectif = objectifRepository.save(existingObjectif);
        }
    }

    @DeleteMapping("/{idObjectif}")
    public void deleteObjectif (@PathVariable Integer idObjectif)throws Exception{
        this.iobjectifService.deleteObjectif(idObjectif);
    }
    @GetMapping("/getAllByuser/{userName}")
        public ResponseEntity<List<Objectif>> findByUserName(@PathVariable String userName)throws Exception{
            return ResponseEntity.ok(this.iobjectifService.findByUserName(userName));
    }

    @GetMapping("/realized")
    public List<Objectif> getRealisedObjectifsForUserBetweenDates(
            @RequestParam("userName") String userName,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return this.iobjectifService.findRealisedObjectifsForUserBetweenDates(userName, startDate, endDate);
    }

    @GetMapping("/Done/{userName}")
    public  Integer nbrObjectifDone(@PathVariable String userName){
        return this.objectifRepository.nbrObjectifDone(userName);
    }

    @GetMapping("/Inprogress/{userName}")
    public  Integer nbrObjectifInprogress(@PathVariable String userName){
        return this.objectifRepository.nbrObjectifInprogress(userName);
    }
    @GetMapping("/all/{userName}")
    public  Integer nbrObjectifTotaleParuser(@PathVariable String userName){
        return this.objectifRepository.nbrObjectifTotaleParuser(userName);
    }

    @GetMapping("/MsgNotif/{userName}")
    public String messageNotif(@PathVariable String userName){
        List<String> titles = iobjectifService.objectisNotDone(userName);
        String message = "";
        message += String.join("Vous avez ratez ces objectifs,", titles);
        return message;
    }
}

