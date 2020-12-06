package com.demo.clean.blog.feed.adapters;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ShowFeedResponse {

    private List<PostResponse> result = new ArrayList<>();
    private int total;
    private int current;


}
