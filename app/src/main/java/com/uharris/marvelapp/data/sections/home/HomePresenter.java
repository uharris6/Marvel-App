package com.uharris.marvelapp.data.sections.home;

import android.app.Activity;

import com.uharris.marvelapp.data.entities.Data;
import com.uharris.marvelapp.data.interactors.home.HomeInteractor;
import com.uharris.marvelapp.data.interactors.home.HomeProvider;
import com.uharris.marvelapp.data.routing.IBaseRouting;

/**
 * Created by uharris on 2/8/17.
 */

public class HomePresenter implements HomeContract.ActionListener, HomeProvider.DataOutput {

    HomeContract.View view;
    IBaseRouting router;
    HomeProvider.Interactor interactor;

    public HomePresenter(HomeContract.View view, IBaseRouting router) {
        this.view = view;
        this.router = router;

        interactor = new HomeInteractor(this);
    }

    @Override
    public void getComics(int offset) {
        interactor.getComics(offset);
    }

    @Override
    public void searchComics(int offset, String search) {
        interactor.searchComics(offset, search);
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void comicDetail(Activity activity, int id) {
        router.comicDetail(activity, id);
    }

    @Override
    public void getComicsSuccess(Data data) {
        if(view != null){
            view.onSuccess(data);
        }
    }

    @Override
    public void getComicsError(String error) {
        if(view != null){
            view.onError(error);
        }
    }
}
