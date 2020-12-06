package com.demo.clean.blog.feed.application;

import com.demo.clean.blog.feed.adapters.ShowFeedResponse;

public interface FeedActions {

    ShowFeedResponse showMostRecentPosts(int page);

    ShowFeedResponse showMostFavoredPosts(int page);
}
