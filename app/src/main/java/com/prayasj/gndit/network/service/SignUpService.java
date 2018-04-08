package com.prayasj.gndit.network.service;


import com.prayasj.gndit.model.UserInfo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SignUpService {
  @POST("/api/signup")
  Call<Void> signup(@Body UserInfo userInfo);
}
