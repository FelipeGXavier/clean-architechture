package com.demo.clean.blog.post.adapters;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdatePostLinkRequest {

    private Long user;

    @JsonProperty("old_link")
    private String oldLink;

    private String link;
}
