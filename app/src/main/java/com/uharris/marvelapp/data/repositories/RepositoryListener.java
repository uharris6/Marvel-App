package com.uharris.marvelapp.data.repositories;

/**
 * Created by Ulises.harris on 4/29/16.
 */
public interface RepositoryListener<T> {

    void onSuccess(T response);

    void onFailed(String error);
}
