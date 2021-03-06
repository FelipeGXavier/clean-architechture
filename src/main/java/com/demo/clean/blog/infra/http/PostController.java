package com.demo.clean.blog.infra.http;

import com.demo.clean.blog.adapters.CreateCommentRequest;
import com.demo.clean.blog.adapters.CreatePostRequest;
import com.demo.clean.blog.adapters.UpdatePostLinkRequest;
import com.demo.clean.blog.application.CommentPost;
import com.demo.clean.blog.application.CreatePost;
import com.demo.clean.blog.application.UpdatePostLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/post")
public class PostController {

    private final CreatePost createPostUseCase;
    private final UpdatePostLink updatePostLinkUseCase;
    private final CommentPost commentPostUseCase;

    @Autowired
    public PostController(CreatePost createPostUseCase, UpdatePostLink updatePostLinkUseCase, CommentPost commentPostUseCase) {
        this.createPostUseCase = createPostUseCase;
        this.updatePostLinkUseCase = updatePostLinkUseCase;
        this.commentPostUseCase = commentPostUseCase;
    }

    @PostMapping
    public ResponseEntity<Void> createPost(@RequestBody CreatePostRequest request) {
        this.createPostUseCase.execute(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{postTitle}")
    public ResponseEntity<Void> updatePostLink(@RequestBody UpdatePostLinkRequest request, @PathVariable String postTitle) {
        this.updatePostLinkUseCase.execute(request, postTitle);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{postId}/comment")
    public ResponseEntity<Void> addCommentPost(@RequestBody CreateCommentRequest request, @PathVariable String postId){
        this.commentPostUseCase.execute(postId, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
