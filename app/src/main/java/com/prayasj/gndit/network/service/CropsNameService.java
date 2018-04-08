package com.prayasj.gndit.network.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CropsNameService {
  @GET("api/crops")
  Call<List<String>> getCropName();
}
