package com.prayasj.gndit.presenter;


import android.util.Log;

import com.prayasj.gndit.Activity.DashboardActivity;
import com.prayasj.gndit.model.CropRequest;
import com.prayasj.gndit.network.service.CropRequestService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardPresenter {
  private CropRequestService cropRequestService;

  public DashboardPresenter(CropRequestService cropRequestService){

    this.cropRequestService = cropRequestService;
  }

  public void getCropRequests(){
    cropRequestService.getCropRequest().enqueue(new Callback<List<CropRequest>>() {
      @Override
      public void onResponse(Call<List<CropRequest>> call, Response<List<CropRequest>> response) {
        Log.d("prayas","success:" + response.body().get(0).getPrice());
      }

      @Override
      public void onFailure(Call<List<CropRequest>> call, Throwable throwable) {
        Log.d("prayas","failure:");

      }
    });
  }

}
