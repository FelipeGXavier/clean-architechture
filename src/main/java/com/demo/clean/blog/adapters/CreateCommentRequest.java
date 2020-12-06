package com.demo.clean.blog.adapters;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreateCommentRequest {

    private String body;

    @JsonProperty("user_id")
    private Long userId;
}
