package com.prayasj.gndit.presenter;


import com.prayasj.gndit.network.service.CropRequestService;
import com.prayasj.gndit.network.service.CropsNameService;
import com.prayasj.gndit.views.MakecropRequestView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MakeCropRequestPresenter {
  private CropRequestService cropRequestService;
  private CropsNameService cropsNameService;
  private MakecropRequestView makecropRequestView;


  public MakeCropRequestPresenter(CropRequestService cropRequestService, CropsNameService cropsNameService, MakecropRequestView makecropRequestView){
    this.cropRequestService = cropRequestService;
    this.cropsNameService = cropsNameService;
    this.makecropRequestView = makecropRequestView;
  }


  public void renderCropsList(){
    makecropRequestView.showProgressLoader();
    cropsNameService.getCropName().enqueue(new Callback<List<String>>() {
      @Override
      public void onResponse(Call<List<String>> call, Response<List<String>> response) {
        if (response.isSuccessful()){
          makecropRequestView.showCropsName(response.body());
        }
      }

      @Override
      public void onFailure(Call<List<String>> call, Throwable throwable) {

      }
    });

  }

  public void saveCropRequest(){
    makecropRequestView.showProgressLoader();
    cropRequestService.saveCropRequest(makecropRequestView.getCropRequestInfo()).enqueue(new Callback<Void>() {
      @Override
      public void onResponse(Call<Void> call, Response<Void> response) {
        if(response.isSuccessful()){
          makecropRequestView.onSuccessful();
        }
      }

      @Override
      public void onFailure(Call<Void> call, Throwable throwable) {

      }
    });
  }
}
