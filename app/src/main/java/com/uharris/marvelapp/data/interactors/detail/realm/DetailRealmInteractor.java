package com.uharris.marvelapp.data.interactors.detail.realm;

import com.uharris.marvelapp.data.entities.Comic;
import com.uharris.marvelapp.data.repositories.RepositoryListener;
import com.uharris.marvelapp.data.repositories.bd.ComicsRealmRepository;
import com.uharris.marvelapp.data.repositories.bd.ComicsRealmRepositoryImpl;

import io.realm.Realm;

/**
 * Created by uharris on 2/8/17.
 */

public class DetailRealmInteractor implements DetailRealmProvider.Interactor {

    DetailRealmProvider.DataOutput output;
    ComicsRealmRepository repository;

    public DetailRealmInteractor(Realm realm, DetailRealmProvider.DataOutput output) {
        this.output = output;
        repository = new ComicsRealmRepositoryImpl(realm);
    }

    @Override
    public void saveComic(Comic comic) {
        repository.saveComic(comic, new RepositoryListener<Comic>() {
            @Override
            public void onSuccess(Comic response) {
                output.savedComicSuccess(response);
            }

            @Override
            public void onFailed(String error) {
                output.onError(error);
            }
        });
    }

    @Override
    public void getComic(int id) {
        repository.getComic(id, new RepositoryListener<Comic>() {
            @Override
            public void onSuccess(Comic response) {
                output.getComicRealm(response);
            }

            @Override
            public void onFailed(String error) {
                output.onError(error);
            }
        });
    }
}
