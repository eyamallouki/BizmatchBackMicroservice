package com.esprit.Bizmatch.paiementMS.Controlleur;

import com.esprit.Bizmatch.paiementMS.Entity.Formation;
import com.esprit.Bizmatch.paiementMS.Repository.FormationRepository;
import com.esprit.Bizmatch.paiementMS.Service.IforamtionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api")
@CrossOrigin(origins = "http://localhost:4200")

public class FormationController {
private final IforamtionService iforamtionService;
    private final FormationRepository formationRepository;


    @PostMapping("/addFormation")
    public Formation addFormation(@RequestBody Formation f) {

       return this.iforamtionService.AddFormation(f);
    }


    @GetMapping("/all")
    public List<Formation> getAllformation() {
        return this.iforamtionService.findAll();
}

    @GetMapping("/lastidpost")
    public Long lastidpost(){
        return this.formationRepository.findLastInsertedId();
    }


}
