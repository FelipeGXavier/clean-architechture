package com.demo.clean.blog.post.application;

import com.demo.clean.blog.post.adapters.UpdatePostLinkRequest;

public interface UpdatePostLink {

    void updatePostLink(UpdatePostLinkRequest request, String postTitle);
}
