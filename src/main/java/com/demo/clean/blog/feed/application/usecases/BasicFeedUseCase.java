package com.demo.clean.blog.feed.application.usecases;

import com.demo.clean.blog.feed.adapters.ShowFeedResponse;
import com.demo.clean.blog.feed.adapters.presenters.ShowFeedPresenter;
import com.demo.clean.blog.feed.application.FeedActions;
import com.demo.clean.blog.post.domain.Post;
import com.demo.clean.blog.post.infra.persistence.repositories.PostRepository;
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
