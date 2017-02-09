package com.uharris.marvelapp.data.sections.home;

import android.app.Activity;

import com.uharris.marvelapp.data.entities.Data;

/**
 * Created by uharris on 2/8/17.
 */

public interface HomeContract {

    interface View{

        void showDialog(boolean show);

        void onError(String error);

        void onSuccess(Data data);
    }

    interface ActionListener{

        void getComics(int offset);

        void searchComics(int offset, String search);

        void onDestroy();

        void comicDetail(Activity activity, int id);
    }
}
