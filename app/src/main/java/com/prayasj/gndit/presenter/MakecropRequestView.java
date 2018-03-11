package com.prayasj.gndit.presenter;


import java.util.List;

public interface MakecropRequestView {
  void showProgressLoader();
  void showCropsName(List<String> responseBody);
}
