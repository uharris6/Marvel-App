package com.uharris.marvelapp.data.entities;

import io.realm.RealmObject;

/**
 * Created by uharris on 2/8/17.
 */

public class Character extends RealmObject {
    String resourceURI;
    String name;

    public String getResourceURI() {
        return resourceURI;
    }

    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
