package com.demo.clean.accounting.adapter;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class DashboardResponse {

    private String login;
    @JsonProperty("most_rated_post")
    private PostPreview mostRatedPost;
    @JsonProperty("most_recent_post")
    private PostPreview mostRecentPost;
    @JsonProperty("total_posts")
    private int totalPosts;
    @JsonProperty("total_favorites")
    private int totalFavorites;
    @JsonProperty("total_followers")
    private int totalFollowers;
    private List<String> followers = new ArrayList<>();
}
