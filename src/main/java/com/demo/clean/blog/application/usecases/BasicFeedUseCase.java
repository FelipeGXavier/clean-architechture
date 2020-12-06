package com.demo.clean.blog.application.usecases;

import com.demo.clean.blog.adapters.ShowFeedResponse;
import com.demo.clean.blog.adapters.presenters.ShowFeedPresenter;
import com.demo.clean.blog.application.FeedActions;
import com.demo.clean.blog.domain.Post;
import com.demo.clean.blog.infra.persistence.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BasicFeedUseCase implements FeedActions {

    private final int SLICE_SIZE = 10;
    private final PostRepository postRepository;
    private final ShowFeedPresenter presenter;

    @Autowired
    public BasicFeedUseCase(PostRepository postRepository, ShowFeedPresenter presenter) {
        this.postRepository = postRepository;
        this.presenter = presenter;
    }

    @Override
    public ShowFeedResponse showMostRecentPosts(int page) {
        var pageRequest = PageRequest.of(page, SLICE_SIZE, Sort.Direction.DESC);
        Page<Post> posts = this.postRepository.findAll(pageRequest);
        return this.presenter.present(posts);
    }

    @Override
    public ShowFeedResponse showMostFavoredPosts(int page) {
        var pageRequest = PageRequest.of(page, SLICE_SIZE, Sort.Direction.DESC);
        Page<Post> posts = this.postRepository.findAll(pageRequest);
        return this.presenter.present(posts);
    }
}
