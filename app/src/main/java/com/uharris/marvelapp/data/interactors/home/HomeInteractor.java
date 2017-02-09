package com.uharris.marvelapp.data.interactors.home;

import com.uharris.marvelapp.data.entities.Data;
import com.uharris.marvelapp.data.repositories.RepositoryListener;
import com.uharris.marvelapp.data.repositories.comics.ComicsRepositoryImpl;

/**
 * Created by uharris on 2/8/17.
 */

public class HomeInteractor implements HomeProvider.Interactor {

    private HomeProvider.DataOutput output;
    private ComicsRepositoryImpl repository = new ComicsRepositoryImpl();

    public HomeInteractor(HomeProvider.DataOutput output) {
        this.output = output;
    }

    @Override
    public void getComics(int offset) {
        repository.getComics(offset, new RepositoryListener<Data>() {
            @Override
            public void onSuccess(Data response) {
                output.getComicsSuccess(response);
            }

            @Override
            public void onFailed(String error) {
                output.getComicsError(error);
            }
        });
    }

    @Override
    public void searchComics(int offset, String search) {
        repository.searchComics(offset, search, new RepositoryListener<Data>() {
            @Override
            public void onSuccess(Data response) {
                output.getComicsSuccess(response);
            }

            @Override
            public void onFailed(String error) {
                output.getComicsError(error);
            }
        });
    }
}
