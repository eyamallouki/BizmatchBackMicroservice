package com.esprit.Bizmatch.partenariatMS.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController
public class FileImageUploadController {
	@PostMapping("/upload-file")
	public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) {
		try {
			System.out.println(file.getOriginalFilename());
			System.out.println(file.getName());
			System.out.println(file.getContentType());
			System.out.println(file.getSize());

			String Path_Directory = "C:\\Centrale-d-achat-P-I4-me-ModuleOffre\\src\\main\\resources\\static\\downloadFile";
			Files.copy(file.getInputStream(), Paths.get(Path_Directory + File.separator + file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);

			// Renvoie une réponse JSON indiquant le succès
			return ResponseEntity.ok().body("Image upload successful");
		} catch (Exception e) {
			// En cas d'erreur, renvoyer une réponse d'erreur
			return ResponseEntity.status(500).body("Image upload failed");
		}
	}
}