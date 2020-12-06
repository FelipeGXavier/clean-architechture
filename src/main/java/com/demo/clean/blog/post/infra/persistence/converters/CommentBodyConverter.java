package com.demo.clean.blog.post.infra.persistence.converters;

import com.demo.clean.blog.post.domain.CommentBody;

import javax.persistence.AttributeConverter;

public class CommentBodyConverter implements AttributeConverter<CommentBody, String> {

    @Override
    public String convertToDatabaseColumn(CommentBody body) {
        return body == null ? null : body.toString();
    }

    @Override
    public CommentBody convertToEntityAttribute(String body) {
        return CommentBody.of(body);
    }
}
