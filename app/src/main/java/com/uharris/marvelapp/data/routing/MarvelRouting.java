package com.uharris.marvelapp.data.routing;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.uharris.marvelapp.ui.activities.DetailActivity;

/**
 * Created by uharris on 2/8/17.
 */

public class MarvelRouting implements IBaseRouting {

    public static String COMIC_ID = "comic_id";

    @Override
    public void comicDetail(Activity activity, int id) {
        Bundle bundle = new Bundle();
        bundle.putInt(COMIC_ID, id);
        startActivity(activity, DetailActivity.class, bundle);
    }

    public void startActivity(Activity activity, Class clazz) {
        Intent intent = new Intent(activity, clazz);
        activity.startActivity(intent);
    }

    public void startActivityTopClear(Context context, Class clazz) {
        Intent intent = new Intent(context, clazz);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }

    public void startActivityFinish(Activity activity, Class clazz) {
        Intent intent = new Intent(activity, clazz);
        activity.startActivity(intent);
        activity.finish();
    }

    public void startActivityFinish(Activity activity, Class clazz, Bundle bundle) {
        Intent intent = new Intent(activity, clazz);
        intent.putExtras(bundle);
        activity.startActivity(intent);
        activity.finish();
    }

    public void startActivity(Activity activity, Class clazz, Bundle bundle) {
        Intent intent = new Intent(activity, clazz);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }


}
