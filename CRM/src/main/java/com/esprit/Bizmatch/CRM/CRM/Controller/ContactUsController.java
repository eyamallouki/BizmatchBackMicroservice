package com.esprit.Bizmatch.CRM.CRM.Controller;

import com.esprit.Bizmatch.CRM.CRM.Entity.AnalyseSentiment;
import com.esprit.Bizmatch.CRM.CRM.Entity.ContactUs;
import com.esprit.Bizmatch.CRM.CRM.Entity.PourcentageSentiment;
import com.esprit.Bizmatch.CRM.CRM.Service.IContactUsService;
import com.esprit.Bizmatch.CRM.CRM.Service.ReclamationNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Claim")
@RequiredArgsConstructor
public class ContactUsController {
    private final IContactUsService contactUsService;

    @PostMapping("/create")
    public ResponseEntity<ContactUs> createContactUs(@RequestBody ContactUs contactUs) {
        ContactUs savedContactUs = contactUsService.createContactUs(contactUs);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedContactUs);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ContactUs>> getAllContactUs() {
        List<ContactUs> contactUsList = contactUsService.getAllContactUs();
        return ResponseEntity.ok(contactUsList);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ContactUs> getContactUsById(@PathVariable Long id) {
        ContactUs contactUs = contactUsService.getContactUsById(id);
        if (contactUs != null) {
            return ResponseEntity.ok(contactUs);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ContactUs> updateContactUs(@PathVariable Long id, @RequestBody ContactUs updatedContactUs) {
        ContactUs contactUs = contactUsService.updateContactUs(id, updatedContactUs);
        if (contactUs != null) {
            return ResponseEntity.ok(contactUs);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteContactUs(@PathVariable Long id) {
        boolean deleted = contactUsService.deleteContactUs(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getbyname")
    public ResponseEntity<ContactUs> getContactUsByName(
            @RequestParam("firstname") String firstname,
            @RequestParam("lastname") String lastname) {
        ContactUs contactUs = contactUsService.getContactUsByFirstnameAndLastname(firstname, lastname);
        if (contactUs != null) {
            return ResponseEntity.ok(contactUs);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/analyser-contenu/{id}")
    public ResponseEntity<AnalyseSentiment> analyserContenuParId(@PathVariable Long id) {
        AnalyseSentiment analyseSentiment = contactUsService.analyserContenuParId(id);
        if (analyseSentiment != null) {
            return ResponseEntity.ok(analyseSentiment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/calculer-pourcentage-sentiment")
    public ResponseEntity<PourcentageSentiment> calculerPourcentageSentiment(@RequestBody ContactUs contactUs) {
        PourcentageSentiment pourcentageSentiment = contactUsService.calculerPourcentageSentiment1(contactUs);
        return ResponseEntity.ok(pourcentageSentiment);
    }

    @GetMapping("/pourcentage-sentiment")
    public ResponseEntity<PourcentageSentiment> afficherPourcentageSentiment() {
        PourcentageSentiment pourcentageSentiment = contactUsService.calculerPourcentageSentimentGlobal();
        if (pourcentageSentiment != null) {
            return ResponseEntity.ok(pourcentageSentiment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

