package com.demo.clean.blog.application;

import com.demo.clean.blog.adapters.UpdatePostLinkRequest;

public interface UpdatePostLink {

    void execute(UpdatePostLinkRequest request, String postTitle);
}
