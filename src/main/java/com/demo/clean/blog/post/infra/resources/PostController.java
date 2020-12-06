package com.demo.clean.blog.post.infra.resources;

import com.demo.clean.blog.post.adapters.CreatePostRequest;
import com.demo.clean.blog.post.adapters.UpdatePostLinkRequest;
import com.demo.clean.blog.post.application.CreatePost;
import com.demo.clean.blog.post.application.UpdatePostLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/post")
public class PostController {

    private final CreatePost createPostUseCase;
    private final UpdatePostLink updatePostLinkUseCase;

    @Autowired
    public PostController(CreatePost createPostUseCase, UpdatePostLink updatePostLinkUseCase) {
        this.createPostUseCase = createPostUseCase;
        this.updatePostLinkUseCase = updatePostLinkUseCase;
    }

    @PostMapping
    public ResponseEntity<Void> createPost(@RequestBody CreatePostRequest request) {
        this.createPostUseCase.createPost(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{postTitle}")
    public ResponseEntity<Void> updatePostLink(@RequestBody UpdatePostLinkRequest request, @PathVariable String postTitle) {
        this.updatePostLinkUseCase.updatePostLink(request, postTitle);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
