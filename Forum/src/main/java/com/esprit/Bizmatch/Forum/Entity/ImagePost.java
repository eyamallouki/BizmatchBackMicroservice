package com.esprit.Bizmatch.Forum.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class ImagePost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(
            name = "type")
    private String type;
    @Column(
            name = "image",
            unique = false,
            nullable = false,
            length = 100000
    )
    private byte[] image;
    @ManyToOne
    private PostBlog postBlog;

    public static ImageBuilder builder() {
        return new ImageBuilder();
    }
    public ImagePost() {
    }

    public ImagePost(final Long id, final String name, final String type, final byte[] image) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.image = image;
    }

    public static class ImageBuilder {
        private Long id;
        private String name;
        private String type;
        private byte[] image;

        ImageBuilder() {
        }

        public ImageBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        public ImageBuilder name(final String name) {
            this.name = name;
            return this;
        }

        public ImageBuilder type(final String type) {
            this.type = type;
            return this;
        }

        public ImageBuilder image(final byte[] image) {
            this.image = image;
            return this;
        }

        public ImagePost build() {
            return new ImagePost(this.id, this.name, this.type, this.image);
        }
    }
}
