package com.demo.clean.blog.infra.persistence.converters;

import com.demo.clean.blog.domain.CommentBody;

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
