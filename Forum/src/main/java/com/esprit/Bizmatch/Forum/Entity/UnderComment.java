package com.esprit.Bizmatch.Forum.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UnderComment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_underComment;

    private String content;
    @Temporal(TemporalType.DATE)
    private Date publishedAt;

    private String image;
    @ManyToOne
    @JsonIgnore
    private Comment comment;

}
