package com.demo.clean.blog.post.domain;

import com.demo.clean.blog.post.infra.persistence.converters.CommentBodyConverter;
import com.demo.clean.accounting.domain.Person;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

    @ManyToOne private Person person;

    public Comment(CommentBody body, Person person) {
        this.body = body;
        this.person = person;
    }
}
