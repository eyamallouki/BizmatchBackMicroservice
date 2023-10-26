package com.esprit.Bizmatch.Forum.Controlleur;

import com.esprit.Bizmatch.Forum.Entity.UnderComment;
import com.esprit.Bizmatch.Forum.Services.IUnderCommentService;
import com.esprit.Bizmatch.Forum.generic.GenericController;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/UnderComment")
public class UnderCommentController extends GenericController<UnderComment,Integer> {
    private final IUnderCommentService iUnderCommentService;

    @PostMapping({"/{id_User}/add/{idpostBlog}/{idcomment}"})
    @ResponseBody
    public UnderComment addUnderComment(@RequestBody UnderComment uc,
                              @PathVariable Integer id_User, @PathVariable Integer idpostBlog,@PathVariable Integer idcomment) {
        return iUnderCommentService.addUnderComment(uc, id_User,idpostBlog,idcomment);
    }
    /*@PostMapping("/{id_UnderComment}/like")
    public void likeUnderComment( int id_PostBlog, int id_Comment, @PathVariable int id_UnderComment) {
        iPostBlogService.like(id_PostBlog, id_Comment,id_UnderComment);
    }
    @PostMapping("/{id_UnderComment}/dislike")
    public void dislikeUnderComment(int id_PostBlog, int id_Comment,@PathVariable int id_UnderComment) {
        iPostBlogService.like(id_PostBlog, id_Comment,id_UnderComment);
    }*/

}
