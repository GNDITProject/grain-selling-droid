package com.prayasj.gndit.views;

import com.prayasj.gndit.model.CropRequest;

import java.util.List;

public interface DashboardView {
  void showCropRequests(List<CropRequest> body);
  void showError();
  void onRefreshDone();
  void showProgressDialog();
  void deleteRequest(CropRequest cropRequest);
}
