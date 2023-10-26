package com.esprit.Bizmatch.Forum.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idcomment;
    private String content;
    @Temporal(TemporalType.DATE)
    private Date publishedAt;
    private String image;
    @JsonIgnore //ki na7iha les post ma3adch yokhrjou
    @ManyToOne
    private PostBlog postBlog;
    @OneToMany(mappedBy = "comment",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<UnderComment> underComments;


    @ManyToOne
    private User user;
}
