package com.prayasj.gndit.network.service;

import com.prayasj.gndit.model.UserInfo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by prayas on 18/2/18.
 */

public interface LoginService {
  @POST ("/api/login")
  Call<Void> login(@Body UserInfo userInfo);
}
