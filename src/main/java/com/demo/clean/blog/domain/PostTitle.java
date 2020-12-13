package com.demo.clean.blog.domain;

import com.demo.clean.shared.DomainException;

public class PostTitle {

    private String title;
    private int MAX_LENGTH = 50;
    private int MIN_LENGTH = 5;

    private PostTitle(String title) {
        if (title == null || title.length() > MAX_LENGTH || title.length() < MIN_LENGTH) {
            throw new DomainException("Invalid title");
        }
        this.title = title;
    }

    public static PostTitle of(String title) {
        return new PostTitle(title);
    }

    @Override
    public String toString() {
        return title;
    }
}
