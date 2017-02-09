package com.uharris.marvelapp.data.entities;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by uharris on 2/8/17.
 */
public class Data {

    int offset;
    int limit;
    int total;
    int count;
    @SerializedName("results")
    ArrayList<Comic> comics;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limiti) {
        this.limit = limiti;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<Comic> getComics() {
        return comics;
    }

    public void setComics(ArrayList<Comic> comics) {
        this.comics = comics;
    }
}
