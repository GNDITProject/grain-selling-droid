package com.prayasj.gndit.network;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.prayasj.gndit.network.ApiParam.BASE_URL;

public class ServiceBuilder {
  public static <T> T build(Class<T> clazz) {
    Retrofit retrofit = new Retrofit.Builder()
      .baseUrl(BASE_URL)
      .addConverterFactory(GsonConverterFactory.create())
      .client(client())
      .build();
    return retrofit.create(clazz);
  }

  private static OkHttpClient client() {
    OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
    clientBuilder.addInterceptor(new Interceptor() {
      @Override
      public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (JwtTokenHolder.getInstance().hasToken()) {
          Request requestWithJwtToken = request.newBuilder()
            .addHeader("jwt_token", JwtTokenHolder.getInstance().getToken())
            .build();
          return chain.proceed(requestWithJwtToken);
        }
        return chain.proceed(request);
      }
    });
    return clientBuilder.build();
  }
}
