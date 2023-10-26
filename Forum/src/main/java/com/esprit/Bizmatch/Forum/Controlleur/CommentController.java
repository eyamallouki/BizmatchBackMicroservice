package com.esprit.Bizmatch.Forum.Controlleur;

import com.esprit.Bizmatch.Forum.Entity.Comment;
import com.esprit.Bizmatch.Forum.Entity.PostBlog;
import com.esprit.Bizmatch.Forum.Repo.CommentRepository;
import com.esprit.Bizmatch.Forum.Services.ICommentService;
import com.esprit.Bizmatch.Forum.generic.GenericController;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Comment")
public class CommentController extends GenericController<Comment,Integer> {
    private final ICommentService iCommentService;
    private final CommentRepository commentRepository;

    @PostMapping({"/{id_User}/{idpostBlog}"})
    @ResponseBody
    public Comment addComment(@PathVariable Integer id_User, @PathVariable Integer idpostBlog,@RequestBody Comment c) {
        return iCommentService.addComment(id_User, idpostBlog, c);
    }


    @DeleteMapping({"/{idcomment}/{idpostBlog}"})
    void deleteComment( @PathVariable Integer idcomment, @PathVariable Integer idpostBlog) {
        iCommentService.deleteComment(idcomment, idpostBlog);
    }

    @GetMapping("/{idpostBlog}")
    public List<Comment> getCommentsForPost(@PathVariable Integer idpostBlog) {
        return  iCommentService.getCommentsForPost(idpostBlog);
    }
    @PostMapping({"/add/{idpostBlog}"})
    @ResponseBody
    public Comment addCommenttopost(@PathVariable Integer idpostBlog,
                              @RequestBody Comment c) {
        return iCommentService.addComment2( idpostBlog,c);
    }


    @GetMapping("/topCommentedPost")
    @ResponseBody
    public PostBlog mostCommentedPost() {
        return this.iCommentService.mostCommentedPost();
    }

    @GetMapping("NbCommentsPerPost/{idpostBlog}")
    public int getNbComment(@PathVariable Integer idpostBlog) {

        return iCommentService.getNbComment(idpostBlog);
    }

    @GetMapping("commentairesPertinents")
    public List<Comment> commentairesPertinents(){
        return  iCommentService.commentairesPertinents();
    }



    @GetMapping("/nbr/total_Compments")
    public Integer countTotalLikes() {
        return iCommentService.countTotaComments();
    }
}
