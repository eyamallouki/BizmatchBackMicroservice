package com.esprit.Bizmatch.Forum.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dislike implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer iddislike;
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private PostBlog postBlog;
}
