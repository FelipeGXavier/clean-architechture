package com.demo.clean.accounting.adapter;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostPreview {

    private String url;
    private String title;
}
