package com.demo.clean.blog.post.domain;

import com.demo.clean.blog.post.infra.converters.PostBodyConverter;
import com.demo.clean.blog.post.infra.converters.PostTitleConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
    private PostTitle title;

}
