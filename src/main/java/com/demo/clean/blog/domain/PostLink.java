package com.demo.clean.blog.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.net.URL;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "post_link")
public class PostLink {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String link;
    @ManyToOne private Post post;

    public PostLink(String link, Post post) {
        if (!this.validUrl(link)) {
            throw new IllegalArgumentException("Invalid url");
        }
        this.link = link;
        this.post = post;
    }

    private boolean validUrl(String url) {
        try {
            new URL(url);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostLink link1 = (PostLink) o;
        return link.equals(link1.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(link);
    }

    @Override
    public String toString() {
        return link;
    }
}
