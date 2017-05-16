package com.example.jezuz1n.hairly.utils;

/**
 * Created by jezuz1n on 16/05/2017.
 */

public interface IGetResults<T> {
    void onSuccess(T object);
    void onFailure(T object);
}
