package com.esprit.Bizmatch.Forum.Services;

import com.esprit.Bizmatch.Forum.Entity.Comment;
import com.esprit.Bizmatch.Forum.Entity.PostBlog;
import com.esprit.Bizmatch.Forum.Entity.UnderComment;
import com.esprit.Bizmatch.Forum.Entity.User;
import com.esprit.Bizmatch.Forum.Repo.CommentRepository;
import com.esprit.Bizmatch.Forum.Repo.PostBlogRepository;
import com.esprit.Bizmatch.Forum.Repo.UnderCommentRepository;
import com.esprit.Bizmatch.Forum.Repo.UserDao;
import com.esprit.Bizmatch.Forum.generic.IGenericServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.io.IOException;

@Service
@RequiredArgsConstructor
public class IUnderCommentServiceImp extends IGenericServiceImp<UnderComment,Integer> implements IUnderCommentService{
    private  final UnderCommentRepository underCommentRepository;
    private  final CommentRepository commentRepository;
    private final PostBlogRepository postBlogRepository;
    private final UserDao userRepository;

    @Override
    public UnderComment addUnderComment(UnderComment uc, Integer iduser, Integer idpostBlog, Integer idcomment) {
        try {
            if (BadWords.verfiyWord(uc.getContent())) {
                return null;
            }
        } catch (IOException var6) {
            var6.printStackTrace();
        }
        User user = userRepository.findById(String.valueOf(iduser)).orElse(null);
        PostBlog post = postBlogRepository.findById(idpostBlog).orElse(null);
        Comment comment = commentRepository.findById(idcomment).orElse(null);
        uc.setComment(comment);
        user.getUnderComments().add(uc);
        return underCommentRepository.save(uc);
    }

}
