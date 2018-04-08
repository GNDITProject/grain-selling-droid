package com.prayasj.gndit.presenter;


import com.prayasj.gndit.network.service.CropRequestService;
import com.prayasj.gndit.network.service.CropsNameService;
import com.prayasj.gndit.views.MakeCropRequestView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MakeCropRequestPresenter {
  private CropRequestService cropRequestService;
  private CropsNameService cropsNameService;
  private MakeCropRequestView makeCropRequestView;


  public MakeCropRequestPresenter(CropRequestService cropRequestService, CropsNameService cropsNameService, MakeCropRequestView makeCropRequestView){
    this.cropRequestService = cropRequestService;
    this.cropsNameService = cropsNameService;
    this.makeCropRequestView = makeCropRequestView;
  }


  public void renderCropsList(){
    makeCropRequestView.showProgressLoader("Loading Crops", "Loading...");
    cropsNameService.getCropName().enqueue(new Callback<List<String>>() {
      @Override
      public void onResponse(Call<List<String>> call, Response<List<String>> response) {
        if (response.isSuccessful()){
          makeCropRequestView.showCropsName(response.body());
        }
      }

      @Override
      public void onFailure(Call<List<String>> call, Throwable throwable) {

      }
    });

  }

  public void saveCropRequest(){
    makeCropRequestView.showProgressLoader("Creating Crop Request", "Sending Request to the Server");
    cropRequestService.saveCropRequest(makeCropRequestView.getCropRequestInfo()).enqueue(new Callback<Void>() {
      @Override
      public void onResponse(Call<Void> call, Response<Void> response) {
        if(response.isSuccessful()){
          makeCropRequestView.onSuccessful();
          return;
        }
        makeCropRequestView.onFailure();
      }

      @Override
      public void onFailure(Call<Void> call, Throwable throwable) {
        makeCropRequestView.onFailure();
      }
    });
  }
}
