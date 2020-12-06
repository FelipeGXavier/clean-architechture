package com.demo.clean.blog.post.infra.persistence.converters;

import com.demo.clean.blog.post.domain.PostBody;

import javax.persistence.AttributeConverter;

public class PostBodyConverter implements AttributeConverter<PostBody, String> {

    @Override
    public String convertToDatabaseColumn(PostBody postBody) {
        return postBody == null ? null : postBody.toString();
    }

    @Override
    public PostBody convertToEntityAttribute(String body) {
        return PostBody.of(body);
    }
}
