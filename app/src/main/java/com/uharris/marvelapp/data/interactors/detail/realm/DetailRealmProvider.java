package com.uharris.marvelapp.data.interactors.detail.realm;

import com.uharris.marvelapp.data.entities.Comic;

/**
 * Created by uharris on 2/8/17.
 */

public interface DetailRealmProvider {

    interface Interactor{
        void saveComic(Comic comic);

        void getComic(int id);
    }

    interface DataOutput{
        void savedComicSuccess(Comic comic);

        void getComicRealm(Comic comic);

        void onError(String error);
    }
}
