package com.esprit.Bizmatch.Forum.Services;

import com.esprit.Bizmatch.Forum.Entity.Comment;
import com.esprit.Bizmatch.Forum.Entity.PostBlog;
import com.esprit.Bizmatch.Forum.Entity.User;
import com.esprit.Bizmatch.Forum.Repo.CommentRepository;
import com.esprit.Bizmatch.Forum.Repo.PostBlogRepository;
import com.esprit.Bizmatch.Forum.Repo.UserDao;
import com.esprit.Bizmatch.Forum.generic.IGenericServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ICommentServiceImp extends IGenericServiceImp<Comment,Integer> implements ICommentService{
    private final CommentRepository commentRepository;
    private final PostBlogRepository postBlogRepository;
    private final UserDao userRepository;
    @Override
    public Comment addComment(Integer iduser, Integer idpostBlog,Comment c) {
        try {
            if (BadWords.verfiyWord(c.getContent())) {
                return null;
            }
        } catch (IOException var6) {
            var6.printStackTrace();
        }

        User user = userRepository.findById(String.valueOf(iduser)).orElse(null);
        PostBlog post = postBlogRepository.findById(idpostBlog).orElse(null);
        if (user != null && post != null ){
            c.setPublishedAt(new Date());
            user.getComment().add(c);
            c.setPostBlog(post);
        }

        return commentRepository.save(c);
    }
    public Comment addComment2( Integer idpostBlog,Comment c) {
        try {
            if (BadWords.verfiyWord(c.getContent())) {
                commentRepository.save(c);
            }
        } catch (IOException var6) {
            var6.printStackTrace();
        }

        PostBlog post = postBlogRepository.findById(idpostBlog).orElse(null);
        if ( post != null ){
            c.setPublishedAt(new Date());
            c.setPostBlog(post);
        }

        return commentRepository.save(c);
    }








    @Override
    public PostBlog mostCommentedPost() {
        List<PostBlog> posts = commentRepository.getPosts();
        return (PostBlog) posts.stream().max((c1, c2) -> {
            return this.commentRepository.countByPostBlog(c1) - this.commentRepository.countByPostBlog(c2);
        }).orElse(null);
    }

    @Override
    public int getNbComment(Integer idpostBlog) {
        PostBlog postBlog = postBlogRepository.findById(idpostBlog).orElse(null);
        return postBlog != null ? this.commentRepository.countByPostBlog(postBlog) : 0; //:0 siggnifie retourner 0 si ce poste n'a aucun comment
    }

    @Override
    public List<Comment> commentairesPertinents() {
        return commentRepository.findCommentsOrderByUnderCommentCountDesc();
    }

    @Override
    public List<Comment> getCommentsForPost(Integer idpostBlog) {
        PostBlog postBlog = postBlogRepository.findById(idpostBlog).orElse(null);
        return postBlog.getComments();
    }

    @Override
    public void deleteComment(Integer idcomment, Integer idpostBlog) {
        PostBlog postBlog = postBlogRepository.findById(idpostBlog).orElse(null);
        Comment comment = commentRepository.findById(idcomment).orElse(null);
        if(comment.getPostBlog()== postBlog){
            commentRepository.delete(comment);
        }
    }

    @Override
    public Integer countTotaComments() {
        return commentRepository.countAllLCommnts();
    }


}
