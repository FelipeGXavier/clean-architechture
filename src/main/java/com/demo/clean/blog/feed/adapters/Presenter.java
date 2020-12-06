package com.demo.clean.blog.feed.adapters;

public interface Presenter<T, E> {

    E present(T response);

}
