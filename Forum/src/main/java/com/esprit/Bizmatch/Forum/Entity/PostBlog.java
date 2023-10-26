package com.esprit.Bizmatch.Forum.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostBlog implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idpostBlog;
    private String title;
    private String content;
    private Date publishedDate;
    @Enumerated(EnumType.STRING)
    private Category category;
    private Boolean isPublic;

    private Integer nbcomment;

    @OneToMany(mappedBy = "postBlog", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"postBlog"})
    private List<ImagePost> imagePosts;

    @OneToMany(mappedBy = "postBlog", cascade = CascadeType.ALL)
    //@JsonIgnore
    private  List<LikeP> likePS;
    @OneToMany(mappedBy = "postBlog", cascade = CascadeType.ALL)
    //@JsonIgnore
    private  List<Dislike> dislikes;
    @ManyToOne
    //@JsonIgnore
    private User user;


    @OneToMany(mappedBy = "postBlog")//,  orphanRemoval = true , cascade = CascadeType.ALL
    private List<Comment> comments;


}




























