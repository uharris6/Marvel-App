package com.uharris.marvelapp.data.entities;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by uharris on 2/8/17.
 */
public class Characters extends RealmObject {
    int available;
    RealmList<Character> items;
    int returned;

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public RealmList<Character> getItems() {
        return items;
    }

    public void setItems(RealmList<Character> items) {
        this.items = items;
    }

    public int getReturned() {
        return returned;
    }

    public void setReturned(int returned) {
        this.returned = returned;
    }
}
