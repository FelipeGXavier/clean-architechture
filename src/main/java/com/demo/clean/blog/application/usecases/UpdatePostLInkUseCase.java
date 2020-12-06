package com.demo.clean.blog.application.usecases;

import com.demo.clean.blog.adapters.UpdatePostLinkRequest;
import com.demo.clean.blog.application.UpdatePostLink;
import com.demo.clean.blog.domain.PostLink;
import com.demo.clean.blog.domain.PostTitle;
import com.demo.clean.blog.infra.persistence.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdatePostLInkUseCase implements UpdatePostLink {

    private final PostRepository postRepository;

    @Autowired
    public UpdatePostLInkUseCase(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public void execute(UpdatePostLinkRequest request, String postTitle) {
        var post =
                this.postRepository
                        .findByAuthorIdAndPostId(request.getUser(), PostTitle.of(postTitle))
                        .orElseThrow(
                                () -> {
                                    throw new IllegalArgumentException("Invalid user or post");
                                });
        var oldPostLink = new PostLink(request.getOldLink(), post);
        var newPostLink = new PostLink(request.getLink(), post);
        post.updateLink(oldPostLink, newPostLink);
        this.postRepository.save(post);
    }
}
