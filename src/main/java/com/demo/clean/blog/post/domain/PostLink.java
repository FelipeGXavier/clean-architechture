package com.demo.clean.blog.post.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

    @Transient
    private final String regex =
            "/[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)?/gi";

    public PostLink(String link) {
        if (!link.matches(regex)) {
            throw new IllegalArgumentException("Invalid link");
        }
        this.link = link;
    }
}
