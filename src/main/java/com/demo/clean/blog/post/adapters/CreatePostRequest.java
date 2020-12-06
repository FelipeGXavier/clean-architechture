package com.demo.clean.blog.post.adapters;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
public class CreatePostRequest {

    private String title;
    private String body;
    private Long user;
    private Set<String> links;

}
