package com.prayasj.gndit.presenter;


import android.util.Log;

import com.prayasj.gndit.model.CropRequest;
import com.prayasj.gndit.network.service.CropRequestService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardPresenter {
  private CropRequestService cropRequestService;
  private DashboardView dashboardView;

  public DashboardPresenter(CropRequestService cropRequestService, DashboardView dashboardView) {

    this.cropRequestService = cropRequestService;
    this.dashboardView = dashboardView;
  }

  public void showCropRequests() {
    cropRequestService.getCropRequest().enqueue(new Callback<List<CropRequest>>() {
      @Override
      public void onResponse(Call<List<CropRequest>> call, Response<List<CropRequest>> response) {
        if (response.isSuccessful()) {
          List<CropRequest> body = response.body();
          dashboardView.showCropRequests(body);
        }
        dashboardView.showError();
      }

      @Override
      public void onFailure(Call<List<CropRequest>> call, Throwable throwable) {
        Log.d("prayas", "failure:");
        dashboardView.showError();
      }
    });
  }

  public void refresh() {
    cropRequestService.getCropRequest().enqueue(new Callback<List<CropRequest>>() {
      @Override
      public void onResponse(Call<List<CropRequest>> call, Response<List<CropRequest>> response) {
        if (response.isSuccessful()) {
          List<CropRequest> body = response.body();
          dashboardView.showCropRequests(body);
          dashboardView.onRefreshDone();
        }
        dashboardView.showError();
      }

      @Override
      public void onFailure(Call<List<CropRequest>> call, Throwable throwable) {
        Log.d("prayas", "failure:");
        dashboardView.showError();
      }
    });

  }

}
