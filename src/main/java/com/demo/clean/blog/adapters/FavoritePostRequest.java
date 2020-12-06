package com.demo.clean.blog.adapters;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FavoritePostRequest {

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("post_external_id")
    private String postExternalId;
}
