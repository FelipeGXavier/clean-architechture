package com.demo.clean.blog.adapters;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@AllArgsConstructor
@Data
public class CreatePostRequest {

    private String title;
    private String body;
    private Long user;
    private Set<String> links;

}
