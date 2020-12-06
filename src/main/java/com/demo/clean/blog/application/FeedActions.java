package com.demo.clean.blog.application;

import com.demo.clean.blog.adapters.ShowFeedResponse;

public interface FeedActions {

    ShowFeedResponse showMostRecentPosts(int page);

    ShowFeedResponse showMostFavoredPosts(int page);
}
