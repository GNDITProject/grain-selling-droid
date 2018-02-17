package com.prayasj.gndit.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.prayasj.gndit.network.ApiParam.BASE_URL;

public class ServiceBuilder {
    public static <T> T build(Class<T> clazz) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(clazz);
    }
}
