package com.uharris.marvelapp.data.interactors.detail;

import com.uharris.marvelapp.data.entities.Data;

/**
 * Created by uharris on 2/8/17.
 */

public interface DetailProvider {

    interface Interactor{
        void getComic(int id);
    }

    interface  DataOutput{
        void getComicSuccess(Data data);

        void getComicError(String error);
    }
}
