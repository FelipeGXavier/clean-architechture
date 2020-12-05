package com.demo.clean.blog.post.domain;

import com.demo.clean.blog.post.infra.converters.CommentBodyConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Convert(converter = CommentBodyConverter.class)
    private CommentBody body;

    private String external_id = UUID.randomUUID().toString();

    public Comment(CommentBody body) {
        this.body = body;
    }
}
