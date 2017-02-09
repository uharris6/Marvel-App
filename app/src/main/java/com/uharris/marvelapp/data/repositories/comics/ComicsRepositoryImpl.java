package com.uharris.marvelapp.data.repositories.comics;

import com.uharris.marvelapp.data.entities.Data;
import com.uharris.marvelapp.data.entities.Response;
import com.uharris.marvelapp.data.repositories.RepositoryListener;
import com.uharris.marvelapp.data.repositories.ServiceHelper;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by uharris on 2/8/17.
 */

public class ComicsRepositoryImpl {

    ServiceHelper serviceHelper = ServiceHelper.getInstance();

    public ComicsRepositoryImpl() {
    }

    public void getComics(int offset, final RepositoryListener<Data> listener) {
        Call<Response> call = serviceHelper.getComicsServices().getComics(offset);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                listener.onSuccess(response.body().getData());
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                listener.onFailed(t.getLocalizedMessage());
            }
        });
    }

    public void searchComics(int offset, String search,final RepositoryListener<Data> listener){
        Call<Response> call =  serviceHelper.getComicsServices().searchComics(offset, search);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                listener.onSuccess(response.body().getData());
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                listener.onFailed(t.getLocalizedMessage());
            }
        });
    }

    public void getComic(int id, final RepositoryListener<Data> listener){
        Call<Response> call = serviceHelper.getComicsServices().getComic(id);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                listener.onSuccess(response.body().getData());
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                listener.onFailed(t.getLocalizedMessage());
            }
        });
    }
}
