package com.uharris.marvelapp.data.entities;

import io.realm.RealmObject;

/**
 * Created by uharris on 2/8/17.
 */
public class Price extends RealmObject {

    String type;
    double price;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
