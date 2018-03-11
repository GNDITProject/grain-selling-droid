package com.prayasj.gndit.presenter;


import android.widget.ArrayAdapter;

import com.prayasj.gndit.Activity.MakeCropRequestActivity;
import com.prayasj.gndit.R;
import com.prayasj.gndit.network.ServiceBuilder;
import com.prayasj.gndit.network.service.CropsNameService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MakeCropRequestPresenter {
  private CropsNameService cropsNameService;
  private MakecropRequestView makecropRequestView;


  public MakeCropRequestPresenter(CropsNameService cropsNameService, MakecropRequestView makecropRequestView){
    this.cropsNameService = cropsNameService;
    this.makecropRequestView = makecropRequestView;
  }
  public void getCropName(){
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
}
