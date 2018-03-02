package com.prayasj.gndit.network.service;

import com.prayasj.gndit.model.CropRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CropRequestService {
  @GET("/api/crop-request")
  Call<List<CropRequest>> getCropRequest();
}
