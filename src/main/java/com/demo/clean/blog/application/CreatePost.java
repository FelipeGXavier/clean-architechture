package com.demo.clean.blog.application;

import com.demo.clean.blog.adapters.CreatePostRequest;

public interface CreatePost {

    void execute(CreatePostRequest request);
}
