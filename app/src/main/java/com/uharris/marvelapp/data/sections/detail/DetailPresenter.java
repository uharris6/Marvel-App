package com.uharris.marvelapp.data.sections.detail;

import com.uharris.marvelapp.data.entities.Comic;
import com.uharris.marvelapp.data.entities.Data;
import com.uharris.marvelapp.data.interactors.detail.DetailInteractor;
import com.uharris.marvelapp.data.interactors.detail.DetailProvider;
import com.uharris.marvelapp.data.interactors.detail.realm.DetailRealmInteractor;
import com.uharris.marvelapp.data.interactors.detail.realm.DetailRealmProvider;

import io.realm.Realm;

/**
 * Created by uharris on 2/8/17.
 */

public class DetailPresenter implements DetailContract.ActionListener, DetailProvider.DataOutput,
        DetailRealmProvider.DataOutput {

    DetailContract.View view;
    DetailProvider.Interactor interactor;
    DetailRealmProvider.Interactor realmInteractor;

    public DetailPresenter(Realm realm, DetailContract.View view) {
        this.view = view;
        interactor = new DetailInteractor(this);
        realmInteractor = new DetailRealmInteractor(realm, this);
    }

    @Override
    public void getComic(int id) {
        interactor.getComic(id);
    }

    @Override
    public void saveComic(Comic comic) {
        realmInteractor.saveComic(comic);
    }

    @Override
    public void getComicRealm(int id) {
        realmInteractor.getComic(id);
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void getComicSuccess(Data data) {
        if (view != null) {
            view.onSucces(data);
        }
    }

    @Override
    public void getComicError(String error) {
        if (view != null) {
            view.onError(error);
        }
    }

    @Override
    public void savedComicSuccess(Comic comic) {
        if (view != null) {
            view.onSuccesSavedComic(comic);
        }
    }

    @Override
    public void getComicRealm(Comic comic) {
        if(view != null){
            view.onSuccessGetComicRealm(comic);
        }
    }

    @Override
    public void onError(String error) {
        if (view != null) {
            view.onError(error);
        }
    }
}
