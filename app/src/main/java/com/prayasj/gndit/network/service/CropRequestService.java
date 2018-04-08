package com.prayasj.gndit.network.service;

import com.prayasj.gndit.dto.CropRequestDto;
import com.prayasj.gndit.model.CropRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CropRequestService {
  @GET("/api/crop-request")
  Call<List<CropRequest>> getCropRequest();

  @POST("api/crop-request")
  Call<Void> saveCropRequest(@Body CropRequestDto cropRequestDto);

  @DELETE("api/crop-request/{requestId}")
  Call<Void> deleteCropRequest(@Path("requestId") String requestId);
}
