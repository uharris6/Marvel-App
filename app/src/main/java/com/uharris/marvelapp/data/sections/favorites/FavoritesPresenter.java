package com.uharris.marvelapp.data.sections.favorites;

import android.app.Activity;

import com.uharris.marvelapp.data.entities.Comic;
import com.uharris.marvelapp.data.interactors.favorites.FavoritesInteractor;
import com.uharris.marvelapp.data.interactors.favorites.FavoritesProvider;
import com.uharris.marvelapp.data.routing.IBaseRouting;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by uharris on 2/8/17.
 */

public class FavoritesPresenter implements FavoritesContract.ActionListener, FavoritesProvider.DataOutput {

    FavoritesContract.View view;
    IBaseRouting router;
    FavoritesProvider.Interactor interactor;

    public FavoritesPresenter(Realm realm, FavoritesContract.View view, IBaseRouting router) {
        this.view = view;
        this.router = router;

        interactor = new FavoritesInteractor(realm, this);
    }

    @Override
    public void getFavoritesComics() {
        interactor.getFavorites();
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void detailComic(Activity activity, Comic comic) {
        router.comicDetail(activity, comic.getId());
    }

    @Override
    public void getFavoritesSuccess(RealmResults<Comic> comics) {
        if(view != null){
            view.onSuccess(comics);
        }
    }

    @Override
    public void getFavoritesError(String error) {
        if(view != null){
            view.onError(error);
        }
    }
}
