package com.uharris.marvelapp.data.interactors.favorites;

import com.uharris.marvelapp.data.entities.Comic;
import com.uharris.marvelapp.data.repositories.RepositoryListener;
import com.uharris.marvelapp.data.repositories.bd.ComicsRealmRepository;
import com.uharris.marvelapp.data.repositories.bd.ComicsRealmRepositoryImpl;
import com.uharris.marvelapp.data.sections.favorites.FavoritesContract;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by uharris on 2/8/17.
 */

public class FavoritesInteractor implements FavoritesProvider.Interactor {

    FavoritesProvider.DataOutput output;
    ComicsRealmRepository repository;

    public FavoritesInteractor(Realm realm, FavoritesProvider.DataOutput output) {
        this.output = output;
        repository = new ComicsRealmRepositoryImpl(realm);

    }

    @Override
    public void getFavorites() {
        repository.getComics(new RepositoryListener<RealmResults<Comic>>() {
            @Override
            public void onSuccess(RealmResults<Comic> response) {
                output.getFavoritesSuccess(response);
            }

            @Override
            public void onFailed(String error) {
                output.getFavoritesError(error);
            }
        });
    }
}
