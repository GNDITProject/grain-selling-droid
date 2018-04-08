package com.prayasj.gndit.views;


import com.prayasj.gndit.dto.CropRequestDto;

import java.util.List;

public interface MakeCropRequestView {
  void showProgressLoader(String title, String message);

  void showCropsName(List<String> responseBody);

  CropRequestDto getCropRequestInfo();

  void onSuccessful();

  void onFailure();
}
