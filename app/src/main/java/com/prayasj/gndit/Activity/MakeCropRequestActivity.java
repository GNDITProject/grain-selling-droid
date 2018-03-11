package com.prayasj.gndit.Activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.prayasj.gndit.R;
import com.prayasj.gndit.network.ServiceBuilder;
import com.prayasj.gndit.network.service.CropsNameService;
import com.prayasj.gndit.presenter.MakeCropRequestPresenter;
import com.prayasj.gndit.presenter.MakecropRequestView;

import java.util.List;


public class MakeCropRequestActivity extends AppCompatActivity implements MakecropRequestView{


  private Spinner cropRequestSpinner;
  private ProgressDialog progressDialog;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_make_request);
    cropRequestSpinner = findViewById(R.id.crop_name);
    MakeCropRequestPresenter makeCropRequestPresenter =
      new MakeCropRequestPresenter(ServiceBuilder.build(CropsNameService.class),this);
    makeCropRequestPresenter.getCropName();
  }

  @Override
  public void showProgressLoader() {
    progressDialog = new ProgressDialog(MakeCropRequestActivity.this);
    progressDialog.setTitle("Loading Crops Name");
    progressDialog.setMessage("Loading...");
    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    progressDialog.setCancelable(false);
    progressDialog.show();
  }

  @Override
  public void showCropsName(List<String> responseBody) {
    ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(
       MakeCropRequestActivity.this,
        R.layout.support_simple_spinner_dropdown_item,
        android.R.id.text1,
        responseBody);
    cropRequestSpinner.setAdapter(spinnerAdapter);
    progressDialog.hide();
  }
}
