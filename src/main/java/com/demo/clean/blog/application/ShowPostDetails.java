package com.demo.clean.blog.application;

import com.demo.clean.blog.adapters.ShowPostResponse;

public interface ShowPostDetails {

    ShowPostResponse execute(String title);
}
