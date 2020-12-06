package com.demo.clean.blog.application;

import com.demo.clean.blog.adapters.FavoritePostRequest;
import com.demo.clean.blog.adapters.FollowPersonRequest;

public interface SocialActions {

    void followPerson(FollowPersonRequest request);

    void favoritePost(FavoritePostRequest request);
}
