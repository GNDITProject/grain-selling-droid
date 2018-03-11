package com.prayasj.gndit.network.service;


import com.prayasj.gndit.model.UserProfileInfo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserProfileService {
  @POST("/api/profile")
  Call<Void> saveProfile(@Body UserProfileInfo userProfileInfo);
}
