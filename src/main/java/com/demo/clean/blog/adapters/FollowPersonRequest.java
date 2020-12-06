package com.demo.clean.blog.adapters;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FollowPersonRequest {

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("followed_id")
    private Long followedId;
}
