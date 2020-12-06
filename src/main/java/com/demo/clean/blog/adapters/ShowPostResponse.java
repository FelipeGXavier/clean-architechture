package com.demo.clean.blog.adapters;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ShowPostResponse {

    private String content;
    private List<String> relatedLinks;
    private String author;
    private String postedAt;
    private int countFavorites;
    private int countComments;
    private List<ShowCommentResponse> comments;
}
