package com.prayasj.gndit.Activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.prayasj.gndit.R;
import com.prayasj.gndit.dto.CropRequestDto;
import com.prayasj.gndit.network.ServiceBuilder;
import com.prayasj.gndit.network.service.CropRequestService;
import com.prayasj.gndit.network.service.CropsNameService;
import com.prayasj.gndit.presenter.MakeCropRequestPresenter;
import com.prayasj.gndit.views.MakecropRequestView;

import java.util.List;


public class MakeCropRequestActivity extends AppCompatActivity implements MakecropRequestView {


  private Spinner cropRequestSpinner;
  private ProgressDialog progressDialog;
  private MakeCropRequestPresenter makeCropRequestPresenter;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_make_request);
    cropRequestSpinner = findViewById(R.id.crop_name);
    makeCropRequestPresenter = new MakeCropRequestPresenter(ServiceBuilder.build(CropRequestService.class),
      ServiceBuilder.build(CropsNameService.class),
      this);
    makeCropRequestPresenter.renderCropsList();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater menuInflater = getMenuInflater();
    menuInflater.inflate(R.menu.menu_make_crop_request,menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_save:
        makeCropRequestPresenter.saveCropRequest();
        return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public void showProgressLoader() {
    progressDialog = new ProgressDialog(MakeCropRequestActivity.this);
    progressDialog.setTitle("Loading Crops");
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

  @Override
  public CropRequestDto getCropRequestInfo() {
    String price = getStringFromView(R.id.price_make_request);
    String quantity = getStringFromView(R.id.quantity_make_request);
    Spinner cropNameView = findViewById(R.id.crop_name);
    String cropName = cropNameView.getSelectedItem().toString();
    return new CropRequestDto(cropName, quantity, price);
  }

  @Override
  public void onSuccessful() {
    progressDialog.dismiss();
    finish();
    Toast.makeText(MakeCropRequestActivity.this,
      "Request has been Saved",
      Toast.LENGTH_LONG);
  }

  @Override
  public void onFailure() {
    progressDialog.dismiss();
    Toast.makeText(MakeCropRequestActivity.this,
      "Request Not Done",
      Toast.LENGTH_LONG);
  }

  @NonNull
  private String getStringFromView(int viewId) {
    return this.<EditText>findViewById(viewId).getText().toString();
  }
}
