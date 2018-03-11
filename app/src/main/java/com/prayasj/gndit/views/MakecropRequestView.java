package com.prayasj.gndit.views;


import com.prayasj.gndit.dto.CropRequestDto;
import com.prayasj.gndit.model.CropRequest;

import java.util.List;

public interface MakecropRequestView {
  void showProgressLoader();
  void showCropsName(List<String> responseBody);
  CropRequestDto getCropRequestInfo();
  void onSuccessful();

  void onFailure();
}
