package com.esprit.Bizmatch.Forum.Controlleur;

import com.esprit.Bizmatch.Forum.Entity.Category;
import com.esprit.Bizmatch.Forum.Entity.Dislike;
import com.esprit.Bizmatch.Forum.Entity.LikeP;
import com.esprit.Bizmatch.Forum.Entity.PostBlog;
import com.esprit.Bizmatch.Forum.Repo.CommentRepository;
import com.esprit.Bizmatch.Forum.Repo.PostBlogRepository;
import com.esprit.Bizmatch.Forum.Services.IPostBlogService;
import com.esprit.Bizmatch.Forum.generic.GenericController;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/PostBlog")
public class PostBlogController extends GenericController<PostBlog,Integer> {
    private final IPostBlogService iPostBlogService;
    private final PostBlogRepository postBlogRepository;
    private final CommentRepository commentRepository;


    @PutMapping("/addAndAssignPostBlogToUser/{id_User}")
    public void addAndAssignPostBlogToUser(@RequestBody PostBlog p,
                                     @PathVariable Integer id_User) {

      iPostBlogService.addAndAssignPostBlogToUser(p,id_User);
    }

    @GetMapping("/lastidpost")
    public Long lastidpost(){
        return this.postBlogRepository.findLastInsertedId();
    }


    @GetMapping("/chercher/{category}")
    public ResponseEntity<List<PostBlog>> findByCategoryOrderByPublishedDateDesc(@PathVariable Category category)throws  Exception{
        return ResponseEntity.ok(iPostBlogService.findByCategoryOrderByPublishedDateDesc(category));
    }
    @GetMapping("/chercher2/{username}")
    public  ResponseEntity<List<PostBlog>>findByUserUserNameOrderByPublishedDateDesc
            (@PathVariable String username)throws  Exception{
        return ResponseEntity.ok(iPostBlogService.findByUserUserNameOrderByPublishedDateDesc(username));
    }
    @PostMapping("/like/{idpostBlog}")
    public ResponseEntity<LikeP> addAndAssignLikeToPostAndUser(@RequestBody LikeP l
            , @PathVariable Integer idpostBlog)throws  Exception{
        return ResponseEntity.ok(iPostBlogService.addAndAssignLikeToPostAndUser(l,idpostBlog));
    }
   /* @PostMapping("/dislike/{id_User}/{idpostBlog}")
    public ResponseEntity <Dislike> addAndAssignDislikeToPostAndUser(@RequestBody Dislike d,
                                                                     @PathVariable Integer id_User,
                                                                     @PathVariable Integer idpostBlog)
                                                                                throws Exception{
        return ResponseEntity.ok(iPostBlogService.addAndAssignDislikeToPostAndUser(d,id_User,idpostBlog));
    }*/
     @PostMapping("/dislike/{idpostBlog}")
    public ResponseEntity <Dislike> addAndAssignDislikeToPostAndUser(@RequestBody Dislike d,

                                                                     @PathVariable Integer idpostBlog)
                                                                                throws Exception{
        return ResponseEntity.ok(iPostBlogService.addAndAssignDislikeToPostAndUser(d,idpostBlog));
    }
    @GetMapping("/nbrPostByUser/{id_User}")
    public ResponseEntity  nbrPostByUser(@PathVariable Integer id_User) throws Exception{
        return ResponseEntity.ok(iPostBlogService.nbrPostByUser(id_User));
    }

    @GetMapping("/most_categoryUsed")
    public ResponseEntity most_categoryUsed()throws Exception{
        return  ResponseEntity.ok( iPostBlogService.most_categoryUsed());
    }

    @GetMapping("/hi/{idpostBlog}")
    public ResponseEntity<PostBlog> findPostByidpost(@PathVariable Integer idpostBlog)throws Exception{
        return ResponseEntity.ok (postBlogRepository.findById(idpostBlog).orElse(null));
    }

    @DeleteMapping("/delete/{id_User}/{idpostBlog}")
    public void deletePostByUser( @PathVariable Integer id_User,@PathVariable Integer idpostBlog){
          iPostBlogService.deletePostForUser(id_User,idpostBlog);
    }



    @PutMapping("/posts/{idpostBlog}/{idUser}")
    public  void updatePost(@PathVariable Integer idpostBlog, @PathVariable Integer idUser ,@RequestBody PostBlog post) {
        Optional<PostBlog> postToUpdate = postBlogRepository.findById(idpostBlog);

        if (postToUpdate.isPresent()) {
            PostBlog existingPost = postToUpdate.get();
            existingPost.setTitle(post.getTitle());
            existingPost.setContent(post.getContent());
            existingPost.setPublishedDate(post.getPublishedDate());
            existingPost.setCategory(post.getCategory());
            existingPost.setIsPublic(post.getIsPublic());
            existingPost.setNbcomment(post.getNbcomment());
            existingPost.setImagePosts(post.getImagePosts());
            existingPost.setLikePS(post.getLikePS());
            existingPost.setDislikes(post.getDislikes());
            existingPost.setUser(post.getUser());
            existingPost.setComments(post.getComments());

            PostBlog updatedPost = postBlogRepository.save(existingPost);

        }
    }


}

