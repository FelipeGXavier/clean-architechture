package com.demo.clean.blog.post.domain;

import com.demo.clean.blog.post.infra.persistence.converters.PostBodyConverter;
import com.demo.clean.blog.post.infra.persistence.converters.PostTitleConverter;
import com.demo.clean.person.domain.Person;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Convert(converter = PostBodyConverter.class)
    private PostBody body;

    @Convert(converter = PostTitleConverter.class)
    @Column(unique = true, nullable = false)
    private PostTitle title;

    private String external_id = UUID.randomUUID().toString();

    @ManyToOne private Person author;

    public Post(PostBody body, PostTitle title, Person author) {
        this.body = body;
        this.title = title;
        this.author = author;
    }
}
