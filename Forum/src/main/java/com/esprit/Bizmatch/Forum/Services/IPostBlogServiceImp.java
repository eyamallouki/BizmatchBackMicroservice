package com.esprit.Bizmatch.Forum.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.esprit.Bizmatch.Forum.Entity.*;
import com.esprit.Bizmatch.Forum.generic.IGenericServiceImp;
import com.esprit.Bizmatch.Forum.Repo.*;

import java.util.Date;
import java.util.List;


@Service
@RequiredArgsConstructor
public class IPostBlogServiceImp extends IGenericServiceImp<PostBlog,Integer> implements IPostBlogService {
    private final PostBlogRepository postBlogRepository;
    private final CommentRepository commentRepository;
    private final UserDao userRepository;
    private final UnderCommentRepository underCommentRepository;
    private final LikePRepository likePRepository;
    private final DislikeRepository dislikeRepository;


    @Override
    public void addAndAssignPostBlogToUser(PostBlog p, Integer id_User) {
        PostBlog postBlog = postBlogRepository.save(p);
        User user = userRepository.findById(String.valueOf(id_User))
                .orElseThrow(() -> new RuntimeException("Error: user does not exist"));
        if (user != null ) {
            postBlog.setPublishedDate(new Date());
            postBlog.setUser(user);
        }
        postBlogRepository.save(postBlog);

    }
    @Override
    public List<PostBlog> findByUserUserNameOrderByPublishedDateDesc(String username ) {
        return postBlogRepository.findByUserUserNameOrderByPublishedDateDesc(username);
    }
    @Override
    public List<PostBlog> findByCategoryOrderByPublishedDateDesc(Category category) {
        return postBlogRepository.findByCategoryOrderByPublishedDateDesc(category);
    }

    @Override
    public Category most_categoryUsed() {
        //initialise un tableau d'entiers de la longueur du nombre de catégories disponibles.
        int[] counts = new int[Category.values().length];

        // compter le nombre de publications pour chaque catégorie .ordinal donne la postion ds enum
        postBlogRepository.findAll().forEach((p) -> {
            counts[p.getCategory().ordinal()]++;
        });
        // trouver l'indice de la catégorie la plus utilisée
        int maxIndex = 0;
        for (int i = 1; i < counts.length; i++) {
            if (counts[i] > counts[maxIndex]) {
                maxIndex = i;
            }
        }
        return Category.values()[maxIndex];
    }


    @Transactional
    @Override
    public LikeP addAndAssignLikeToPostAndUser(LikeP l, Integer idpostBlog) {

        PostBlog postBlog = postBlogRepository.findById(idpostBlog)
                .orElseThrow(() -> new RuntimeException("Error: post does not exist"));;


                LikeP like1 = likePRepository.save(l);
                like1.setPostBlog(postBlog);




        return null;
    }

    @Transactional
    @Override
    public Dislike addAndAssignDislikeToPostAndUser(Dislike d,Integer idpostBlog) {
        PostBlog postBlog = postBlogRepository.findById(idpostBlog)
                .orElseThrow(() -> new RuntimeException("Error: post does not exist"));

                Dislike dislike = dislikeRepository.save(d);
                dislike.setPostBlog(postBlog);

        return null;
    }

    @Override
    @Transactional
    public void deletePostForUser(Integer id_User, Integer idpostBlog) {
        PostBlog postBlog= postBlogRepository.findById(idpostBlog).orElse(null);
        User user = userRepository.findById(String.valueOf(id_User)).orElse(null);

        // Check if the post belongs to the user
        if (!postBlog.getUser().equals(user)) {
            throw new RuntimeException("Post does not belong to user");
        }
        // Delete the post
        postBlogRepository.delete(postBlog);
        userRepository.save(user);

    }



    @Override
    public int nbrPostByUser(Integer id_User) {
        return postBlogRepository.countPostBlogByUseridUser(id_User);
    }



}
