package com.uharris.marvelapp.data.entities;

import io.realm.RealmObject;

/**
 * Created by uharris on 2/8/17.
 */

public class Creator extends RealmObject {
    String resourceURI;
    String name;
    String role;

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
