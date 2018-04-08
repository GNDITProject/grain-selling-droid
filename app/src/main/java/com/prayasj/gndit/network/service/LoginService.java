package com.prayasj.gndit.network.service;

import com.prayasj.gndit.model.UserInfo;
import com.prayasj.gndit.network.response.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {
  @POST("/api/login")
  Call<LoginResponse> login(@Body UserInfo userInfo);
}
