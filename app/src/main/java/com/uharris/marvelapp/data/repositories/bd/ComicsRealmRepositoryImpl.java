package com.uharris.marvelapp.data.repositories.bd;

import com.uharris.marvelapp.data.entities.Comic;
import com.uharris.marvelapp.data.repositories.RepositoryListener;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * Created by uharris on 2/8/17.
 */

public class ComicsRealmRepositoryImpl implements ComicsRealmRepository {

    private Realm realm;
    private RealmResults<Comic> result;

    public ComicsRealmRepositoryImpl(Realm realm) {
        this.realm = realm;
    }


    @Override
    public void saveComic(final Comic comic, final RepositoryListener<Comic> callback) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Comic comicAux = realm.copyToRealmOrUpdate(comic);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                callback.onSuccess(comic);
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                callback.onFailed(error.getLocalizedMessage());
            }
        });
    }

    @Override
    public void getComics(final RepositoryListener<RealmResults<Comic>> callback) {

        result = realm.where(Comic.class).findAllAsync();
        result.addChangeListener(new RealmChangeListener<RealmResults<Comic>>() {
            @Override
            public void onChange(RealmResults<Comic> element) {
                if(element.size() == 0){
                    callback.onFailed(null);
                }else{
                    callback.onSuccess(element);
                }

            }
        });
        if(result.isLoaded()){
            callback.onSuccess(result);
        }
    }

    @Override
    public void getComic(int id, RepositoryListener<Comic> callback) {
        Comic result = realm.where(Comic.class).equalTo("id", id).findFirst();
        if(result != null){
            callback.onSuccess(result);
        }else{
            callback.onSuccess(null);
        }
    }


}
