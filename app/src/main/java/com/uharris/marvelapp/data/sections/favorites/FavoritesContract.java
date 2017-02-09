package com.uharris.marvelapp.data.sections.favorites;

import android.app.Activity;

import com.uharris.marvelapp.data.entities.Comic;

import io.realm.RealmResults;

/**
 * Created by uharris on 2/8/17.
 */

public interface FavoritesContract {

    interface View{
        void onSuccess(RealmResults<Comic> comics);

        void onError(String error);
    }

    interface ActionListener{
        void getFavoritesComics();

        void onDestroy();

        void detailComic(Activity activity, Comic comic);
    }
}
