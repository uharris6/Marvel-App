package com.uharris.marvelapp.data.interactors.favorites;

import com.uharris.marvelapp.data.entities.Comic;

import io.realm.RealmResults;

/**
 * Created by uharris on 2/8/17.
 */

public interface FavoritesProvider {

    interface Interactor{
        void getFavorites();
    }

    interface DataOutput{
        void getFavoritesSuccess(RealmResults<Comic> comics);

        void getFavoritesError(String error);
    }
}
