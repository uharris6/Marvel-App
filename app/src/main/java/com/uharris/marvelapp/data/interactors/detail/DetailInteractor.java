package com.uharris.marvelapp.data.interactors.detail;

import com.uharris.marvelapp.data.entities.Data;
import com.uharris.marvelapp.data.repositories.RepositoryListener;
import com.uharris.marvelapp.data.repositories.comics.ComicsRepositoryImpl;

/**
 * Created by uharris on 2/8/17.
 */

public class DetailInteractor implements DetailProvider.Interactor {

    DetailProvider.DataOutput output;
    ComicsRepositoryImpl comicsRepository = new ComicsRepositoryImpl();

    public DetailInteractor(DetailProvider.DataOutput output) {
        this.output = output;
    }

    @Override
    public void getComic(int id) {
        comicsRepository.getComic(id, new RepositoryListener<Data>() {
            @Override
            public void onSuccess(Data response) {
                output.getComicSuccess(response);
            }

            @Override
            public void onFailed(String error) {
                output.getComicError(error);
            }
        });
    }
}
