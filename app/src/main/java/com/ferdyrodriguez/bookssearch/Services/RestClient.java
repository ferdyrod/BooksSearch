package com.ferdyrodriguez.bookssearch.Services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ferdyrod on 2/6/17.
 */

public class RestClient {

    public static final String BASE_URL = "https://www.googleapis.com/";

    private static Retrofit retrofit;

    public static <S> S createService(Class<S> serviceClass) {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(serviceClass);
    }

}
