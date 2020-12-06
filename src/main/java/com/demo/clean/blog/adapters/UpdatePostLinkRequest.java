package com.demo.clean.blog.adapters;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdatePostLinkRequest {

    private Long user;

    @JsonProperty("old_link")
    private String oldLink;

    private String link;
}
