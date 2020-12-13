package com.demo.clean.blog.application.usecases;

import com.demo.clean.blog.adapters.CreateCommentRequest;
import com.demo.clean.blog.application.CommentPost;
import com.demo.clean.blog.domain.Comment;
import com.demo.clean.blog.domain.CommentBody;
import com.demo.clean.blog.infra.persistence.repositories.CommentRepository;
import com.demo.clean.blog.infra.persistence.repositories.PostRepository;
import com.demo.clean.shared.DomainException;
import com.demo.clean.shared.LoadLoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentPostUseCase implements CommentPost {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Autowired
    public CommentPostUseCase(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public void execute(String postId, CreateCommentRequest request) {
        var post = this.postRepository.findByExternalId(postId).orElseThrow(() -> {
            throw new DomainException("Post not found");
        });
        var commentBody = CommentBody.of(request.getBody());
        var loggedUser = LoadLoggedUser.load(request.getUserId());
        var comment = new Comment(commentBody, loggedUser, post);
        this.commentRepository.save(comment);
    }
}
