package com.esprit.Bizmatch.Forum.Controlleur;

import com.esprit.Bizmatch.Forum.Entity.LikeP;
import com.esprit.Bizmatch.Forum.Services.ILikeService;
import com.esprit.Bizmatch.Forum.generic.GenericController;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@RequestMapping("/LikeP")
public class LikePController  extends GenericController<LikeP,Integer> {
    private final ILikeService iLikeService;

    @GetMapping("/nbrLike/{idpostBlog}")
    public Integer countByPostBlogId_postBlog(@PathVariable Integer idpostBlog) {
        return iLikeService.countByPostBlogIdpostBlog(idpostBlog);
    }


    @GetMapping("/total_likes")
    public Integer countTotalLikes() {
        return iLikeService.countTotalLikes();
    }

}
