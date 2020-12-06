package com.demo.clean.blog.application;

import com.demo.clean.blog.adapters.CreateCommentRequest;

public interface CommentPost {

    void execute(String postId, CreateCommentRequest request);
}
