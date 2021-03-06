package com.demo.clean.blog.domain;

import com.demo.clean.shared.DomainException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommentBody {

    private String body;
    private final int MAX_LENGTH = 1000;
    private final int MIN_LENGTH = 5;
    private List<String> filtersWords;

    private CommentBody(String body) {
        this.setFiltersWords();
        if (body == null
                || body.length() > MAX_LENGTH
                || body.length() < MIN_LENGTH
                || !this.clean(body)) {
            throw new DomainException("Invalid Comment body");
        }
        this.body = body;
    }

    public static CommentBody of(String body) {
        return new CommentBody(body);
    }

    private void setFiltersWords() {
        this.filtersWords = new ArrayList<>();
        this.filtersWords.addAll(Arrays.asList("foo", "bar", "baz"));
    }

    private boolean clean(String body) {
        var tokens = body.split(" ");
        for (String token : tokens) {
            for (String word : this.filtersWords) {
                if (token.toLowerCase().contains(word)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return body;
    }
}
