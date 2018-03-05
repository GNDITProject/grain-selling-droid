package com.prayasj.gndit.presenter;

import com.prayasj.gndit.model.CropRequest;

import java.util.List;

import retrofit2.Response;

public interface DashboardView {
  void showCropRequests(List<CropRequest> body);
  void showError();
  void onRefreshDone();
  void showProgressDialog();
}
