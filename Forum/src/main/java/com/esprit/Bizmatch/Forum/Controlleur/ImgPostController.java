package com.esprit.Bizmatch.Forum.Controlleur;

import com.esprit.Bizmatch.Forum.Entity.ImagePost;
import com.esprit.Bizmatch.Forum.Entity.PostBlog;
import com.esprit.Bizmatch.Forum.Repo.ImagePostRepository;
import com.esprit.Bizmatch.Forum.Repo.PostBlogRepository;
import com.esprit.Bizmatch.Forum.Services.ImageUploadResponse;
import com.esprit.Bizmatch.Forum.util.ImageUtility;
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
@RequestMapping("/UploadImg")
public class ImgPostController {
  private final ImagePostRepository imagePostRepository;
  private final PostBlogRepository postBlogRepository;

  @Transactional
    @PostMapping({"/upload/{idpostBlog}"})
    public ResponseEntity<ImageUploadResponse> uplaodImage(@RequestParam("image") MultipartFile file, @PathVariable Integer idpostBlog) throws IOException {
       PostBlog postBlog =postBlogRepository.findById(idpostBlog).orElse(null);
       ImagePost imagePost= imagePostRepository.save(ImagePost.builder().name(file.getOriginalFilename()).type(file.getContentType()).image(ImageUtility.compressImage(file.getBytes())).build());
        if(postBlog != null){
            imagePost.setPostBlog(postBlog);
        }
       return ResponseEntity.status(HttpStatus.OK).body(new ImageUploadResponse("Image uploaded successfully: " + file.getOriginalFilename()));
    }

    @GetMapping( path = {"/get/image/{name}"})
    public ResponseEntity<byte[]> getImage(@PathVariable String name) throws IOException {
       Optional<ImagePost> dbImage = this.imagePostRepository.findByName(name);
        return ResponseEntity.ok().contentType(MediaType.valueOf(((ImagePost)dbImage.get()).getType())).body(ImageUtility.decompressImage(((ImagePost)dbImage.get()).getImage()));
    }

    @GetMapping("img/{idpostBlog}")
    public ResponseEntity<byte[]> getImageByPostBlogId(@PathVariable Integer idpostBlog) throws IOException {
        Optional<ImagePost> dbImage = imagePostRepository.findByPostBlog_IdpostBlog(idpostBlog);
        if (dbImage.isPresent()) {
            ImagePost imagePost = dbImage.get();
            return ResponseEntity.ok()
                    .contentType(MediaType.valueOf(imagePost.getType()))
                    .body(ImageUtility.decompressImage(imagePost.getImage()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
