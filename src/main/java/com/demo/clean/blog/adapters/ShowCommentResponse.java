package com.demo.clean.blog.adapters;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ShowCommentResponse {

    private String author;
    private String commentedAt;
    private String body;
}
