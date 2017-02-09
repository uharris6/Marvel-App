package com.uharris.marvelapp.data.interactors.home;

import com.uharris.marvelapp.data.entities.Data;

/**
 * Created by uharris on 2/8/17.
 */

public interface HomeProvider {

    interface Interactor{
        void getComics(int offset);

        void searchComics(int offset, String search);
    }

    interface DataOutput{
        void getComicsSuccess(Data data);

        void getComicsError(String error);
    }
}
