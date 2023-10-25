package com.esprit.Bizmatch.paiementMS.Controlleur;

import com.esprit.Bizmatch.paiementMS.Entity.Formation;
import com.esprit.Bizmatch.paiementMS.Entity.ImageForm;
import com.esprit.Bizmatch.paiementMS.Repository.FormationRepository;
import com.esprit.Bizmatch.paiementMS.Repository.ImageRepository;
import com.esprit.Bizmatch.paiementMS.Service.ImageUploadResponse;
import com.esprit.Bizmatch.paiementMS.util.ImageUtility;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api")


public class ImgController {
    private  final FormationRepository formationRepository;
    private final ImageRepository imageRepository;
    @Transactional
    @PostMapping({"/upload/{idFormation}"})
    public ResponseEntity<ImageUploadResponse> uplaodImage(@RequestParam("image") MultipartFile file, @PathVariable Integer idFormation) throws IOException {
            Formation formation =formationRepository.findById(idFormation).orElse(null);
            ImageForm imageForm= imageRepository.save(ImageForm.builder().name(file.getOriginalFilename()).type(file.getContentType()).image(ImageUtility.compressImage(file.getBytes())).build());
            if(formation != null){
                imageForm.setFormation(formation);
            }
            return ResponseEntity.status(HttpStatus.OK).body(new ImageUploadResponse("Image uploaded successfully: " + file.getOriginalFilename()));
        }

    @GetMapping( path = {"/get/image/{name}"})
    public ResponseEntity<byte[]> getImage(@PathVariable String name) throws IOException {
        Optional<ImageForm> dbImage = this.imageRepository.findByName(name);
        return ResponseEntity.ok().contentType(MediaType.valueOf(((ImageForm)dbImage.get()).getType())).body(ImageUtility.decompressImage(((ImageForm)dbImage.get()).getImage()));
    }

    @GetMapping("/img/{idFormation}")
    public ResponseEntity<byte[]> getImageByPostBlogId(@PathVariable Integer idFormation) throws IOException {
        Optional<ImageForm> dbImage = imageRepository.findByFormation_IdFormation(idFormation);
        if (dbImage.isPresent()) {
            ImageForm imageForm = dbImage.get();
            return ResponseEntity.ok()
                    .contentType(MediaType.valueOf(imageForm.getType()))
                    .body(ImageUtility.decompressImage(imageForm.getImage()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
