package com.demo.clean.blog.post.infra.persistence.converters;

import com.demo.clean.blog.post.domain.PostTitle;

import javax.persistence.AttributeConverter;

public class PostTitleConverter implements AttributeConverter<PostTitle, String> {

    @Override
    public String convertToDatabaseColumn(PostTitle postTitle) {
        return postTitle == null ? null : postTitle.toString();
    }

    @Override
    public PostTitle convertToEntityAttribute(String title) {
        return PostTitle.of(title);
    }
}
