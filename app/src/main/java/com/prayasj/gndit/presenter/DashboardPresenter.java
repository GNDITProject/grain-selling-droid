package com.prayasj.gndit.presenter;


import com.prayasj.gndit.model.CropRequest;
import com.prayasj.gndit.network.service.CropRequestService;
import com.prayasj.gndit.views.DashboardView;

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
    dashboardView.showProgressDialog();
    cropRequestService.getCropRequest().enqueue(new Callback<List<CropRequest>>() {
      @Override
      public void onResponse(Call<List<CropRequest>> call, Response<List<CropRequest>> response) {
        if (response.isSuccessful()) {
          List<CropRequest> body = response.body();
          dashboardView.showCropRequests(body);
          return;
        }
        dashboardView.showError();
      }

      @Override
      public void onFailure(Call<List<CropRequest>> call, Throwable throwable) {
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
          return;
        }
        dashboardView.showError();
      }

      @Override
      public void onFailure(Call<List<CropRequest>> call, Throwable throwable) {
        dashboardView.showError();
      }
    });

  }

  public void deleteRequest(CropRequest cropRequest) {
    cropRequestService.deleteCropRequest(cropRequest.getRequestId()).enqueue(new Callback<Void>() {
      @Override
      public void onResponse(Call<Void> call, Response<Void> response) {
        if (response.isSuccessful()) {
          dashboardView.showProgressDialog();
          refresh();
        }
      }

      @Override
      public void onFailure(Call<Void> call, Throwable t) {
        dashboardView.showError();
      }
    });
  }
}
