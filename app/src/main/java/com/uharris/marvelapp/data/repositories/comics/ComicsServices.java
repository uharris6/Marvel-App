package com.uharris.marvelapp.data.repositories.comics;

import com.uharris.marvelapp.data.entities.Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by uharris on 2/8/17.
 */

public interface ComicsServices {

    @GET("comics?limit=30")
    Call<Response> getComics(@Query("offset") int offset);

    @GET("comics?limit=30")
    Call<Response> searchComics(@Query("offset") int offset, @Query("titleStartsWith") String search);

    @GET("comics/{id}")
    Call<Response> getComic(@Path("id") int id);
}
