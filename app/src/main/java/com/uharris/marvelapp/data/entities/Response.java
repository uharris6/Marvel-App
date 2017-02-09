package com.uharris.marvelapp.data.entities;

/**
 * Created by uharris on 2/8/17.
 */

public class Response {
    int code;
    String status;
    Data data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
