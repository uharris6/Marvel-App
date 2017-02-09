package com.uharris.marvelapp.data.sections.detail;

import com.uharris.marvelapp.data.entities.Comic;
import com.uharris.marvelapp.data.entities.Data;

/**
 * Created by uharris on 2/8/17.
 */

public interface DetailContract {

    interface View{
        void onSucces(Data data);

        void onSuccesSavedComic(Comic comic);

        void onSuccessGetComicRealm(Comic comic);

        void onError(String error);

        void showDialog(boolean show);
    }

    interface ActionListener{
        void getComic(int id);

        void saveComic(Comic comic);

        void getComicRealm(int id);

        void onDestroy();
    }
}
