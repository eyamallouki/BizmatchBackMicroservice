package com.esprit.Bizmatch.Forum.Controlleur;

import com.esprit.Bizmatch.Forum.Entity.Dislike;
import com.esprit.Bizmatch.Forum.Services.IDislikeService;
import com.esprit.Bizmatch.Forum.generic.GenericController;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@RequestMapping("/Dislike")
public class DislikeController extends GenericController<Dislike,Integer> {
    private final IDislikeService iDislikeService;
    @GetMapping("/nbrDislike/{idpostBlog}")
    public Integer countByPostBlogId_postBlog(@PathVariable Integer idpostBlog) {
        return iDislikeService.countByPostBlogIdpostBlog(idpostBlog);
    }

    @GetMapping("/total_dilikes")
    public Integer countTotaDisLikes() {
        return iDislikeService.countTotaDisLikes();
    }

}
