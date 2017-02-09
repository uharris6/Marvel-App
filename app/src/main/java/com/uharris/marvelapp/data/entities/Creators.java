package com.uharris.marvelapp.data.entities;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by uharris on 2/8/17.
 */
public class Creators extends RealmObject {
    int available;
    RealmList<Creator> items;
    int returned;

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public RealmList<Creator> getItems() {
        return items;
    }

    public void setItems(RealmList<Creator> items) {
        this.items = items;
    }

    public int getReturned() {
        return returned;
    }

    public void setReturned(int returned) {
        this.returned = returned;
    }
}
