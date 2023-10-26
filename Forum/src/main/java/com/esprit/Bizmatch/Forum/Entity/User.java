package com.esprit.Bizmatch.Forum.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class User implements Serializable {

    @Id
    private String userName;
    private String userFirstName;
    private String userLastName;
    private String userPassword;
    @Email
    private String userEmail;
    private int isverified;
    private String verificationToken;
    @Pattern(regexp = "[0-9]{8}", message = "Le numéro doit être composé de 8 chiffres")
    private String userNumber;
    private String userCode;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLE",
            joinColumns = {
                    @JoinColumn(name = "USER_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ROLE_ID")
            }
    )
    private Set<Role> role;

    @ManyToOne(cascade = CascadeType.ALL)
    private LikeP  likeP;
    @ManyToOne(cascade = CascadeType.ALL)
    private Dislike dislike;

    @OneToMany( mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<PostBlog> postBlogs;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Comment> comment;


    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<UnderComment> underComments;



}
