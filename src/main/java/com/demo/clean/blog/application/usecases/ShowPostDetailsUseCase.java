package com.demo.clean.blog.application.usecases;

import com.demo.clean.blog.adapters.ShowPostResponse;
import com.demo.clean.blog.adapters.presenters.ShowPostPresenter;
import com.demo.clean.blog.application.ShowPostDetails;
import com.demo.clean.blog.domain.PostTitle;
import com.demo.clean.blog.infra.persistence.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class ShowPostDetailsUseCase implements ShowPostDetails {

    private final PostRepository postRepository;
    private final ShowPostPresenter presenter;

    public ShowPostDetailsUseCase(PostRepository postRepository, ShowPostPresenter presenter) {
        this.postRepository = postRepository;
        this.presenter = presenter;
    }

    @Override
    public ShowPostResponse execute(String title) {
        var post = this.postRepository.findBySlug(PostTitle.of(title)).orElseThrow(() -> {
            throw new IllegalArgumentException("Post not found");
        });
        return this.presenter.present(post);
    }
}
