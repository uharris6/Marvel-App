package com.uharris.marvelapp;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by uharris on 2/8/17.
 */

public class MarvelApplication extends Application {

    @Override
    public void onCreate() {

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().schemaVersion(1)
                .deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(config);
    }
}
