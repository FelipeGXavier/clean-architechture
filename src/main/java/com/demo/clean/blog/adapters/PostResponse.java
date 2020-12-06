package com.demo.clean.blog.adapters;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PostResponse {

    private String title;
    private String author;

    @JsonProperty("posted_at")
    private String postedAt;

    @JsonProperty("minified_body")
    private String minifiedBody;

    @JsonProperty("related_links")
    private List<String> relatedLinks;
}
