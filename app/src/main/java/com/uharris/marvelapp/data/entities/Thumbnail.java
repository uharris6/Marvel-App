package com.uharris.marvelapp.data.entities;

import io.realm.RealmObject;

/**
 * Created by uharris on 2/8/17.
 */
public class Thumbnail extends RealmObject {
    String path;
    String extension;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}
