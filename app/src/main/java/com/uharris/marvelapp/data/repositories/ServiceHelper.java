package com.uharris.marvelapp.data.repositories;

import com.uharris.marvelapp.data.repositories.comics.ComicsServices;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ulises.harris on 4/27/16.
 */
public class ServiceHelper {

    private static final String BASE_URL = "https://gateway.marvel.com:443/v1/public/";

    private static final String PUBLIC_API_KEY = "2359abf629c50fbfd70cf59e778dabed";

    private static final String PRIVATE_API_KEY = "aa879f60b89257f0d4711886a461c65f06ad349d";

    private static Retrofit retrofit;

    private static ServiceHelper instance = null;

    private ComicsServices comicsServices;

    public ServiceHelper() {
        this(BASE_URL);
    }


    public ServiceHelper(String baseUrl) {

        if (retrofit == null) {
            HttpLoggingInterceptor interceptorLog = new HttpLoggingInterceptor();
            interceptorLog.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
            clientBuilder.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();
                    HttpUrl originalHttpUrl = original.url();

                    Long tsLong = System.currentTimeMillis()/1000;
                    String ts = tsLong.toString();

                    String aux = ts.concat(PRIVATE_API_KEY).concat(PUBLIC_API_KEY);
                    String hash = md5(aux);

                    HttpUrl url = originalHttpUrl.newBuilder()
                            .addQueryParameter("apikey", PUBLIC_API_KEY)
                            .addQueryParameter("ts",ts)
                            .addQueryParameter("hash", hash)
                            .build();

                    // Request customization: add request headers
                    Request.Builder requestBuilder = original.newBuilder()
                            .url(url);

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });
            clientBuilder.addInterceptor(interceptorLog);
            OkHttpClient client = clientBuilder.build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();

            comicsServices = retrofit.create(ComicsServices.class);

        }

    }

    public static ServiceHelper getInstance() {
        if (instance == null) {
            instance = new ServiceHelper();
        }

        return instance;
    }

    public ComicsServices getComicsServices() {
        return comicsServices;
    }

    private String md5(String in) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
            digest.reset();
            digest.update(in.getBytes());
            byte[] a = digest.digest();
            int len = a.length;
            StringBuilder sb = new StringBuilder(len << 1);
            for (int i = 0; i < len; i++) {
                sb.append(Character.forDigit((a[i] & 0xf0) >> 4, 16));
                sb.append(Character.forDigit(a[i] & 0x0f, 16));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) { e.printStackTrace(); }
        return null;
    }
}
