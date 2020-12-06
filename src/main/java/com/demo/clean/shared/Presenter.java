package com.demo.clean.shared;

public interface Presenter<T, E> {

    E present(T response);

}
