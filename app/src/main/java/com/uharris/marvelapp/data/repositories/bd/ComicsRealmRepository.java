package com.uharris.marvelapp.data.repositories.bd;

import com.uharris.marvelapp.data.entities.Comic;
import com.uharris.marvelapp.data.repositories.RepositoryListener;

import java.util.ArrayList;

import io.realm.RealmResults;

/**
 * Created by uharris on 2/8/17.
 */

public interface ComicsRealmRepository {

    void saveComic(Comic comic, final RepositoryListener<Comic> callback);

    void getComics(final RepositoryListener<RealmResults<Comic>> callback);

    void getComic(int id, RepositoryListener<Comic> callback);
}
