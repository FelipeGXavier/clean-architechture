package com.demo.clean.blog.domain;

import com.demo.clean.blog.infra.persistence.converters.CommentBodyConverter;
import com.demo.clean.accounting.domain.Person;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @ManyToOne private Post post;

    @Column(name = "commented_at")
    private LocalDateTime commentedAt;

    public Comment(CommentBody body, Person person, Post post) {
        this.body = body;
        this.person = person;
        this.post = post;
    }

    @PrePersist
    public void addTimestamp(){
        this.commentedAt = LocalDateTime.now();
    }
}
