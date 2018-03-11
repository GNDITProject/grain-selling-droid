package com.prayasj.gndit.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.prayasj.gndit.R;
import com.prayasj.gndit.adapter.CropRequestAdapter;
import com.prayasj.gndit.model.CropRequest;
import com.prayasj.gndit.network.ServiceBuilder;
import com.prayasj.gndit.network.service.CropRequestService;
import com.prayasj.gndit.presenter.DashboardPresenter;
import com.prayasj.gndit.views.DashboardView;

import java.util.List;


public class DashboardActivity extends AppCompatActivity implements DashboardView {

  private SwipeRefreshLayout refreshView;
  private ProgressDialog progressDialog;

  static final int CREATE_CROP_REQUEST_CODE = 1;
  private DashboardPresenter dashboardPresenter;

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == CREATE_CROP_REQUEST_CODE && resultCode == RESULT_OK) {
        dashboardPresenter.refresh();
    }
    super.onActivityResult(requestCode, resultCode, data);
  }


  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_dashboard);
    dashboardPresenter = new DashboardPresenter(ServiceBuilder.build(CropRequestService.class), this);
    dashboardPresenter.showCropRequests();

    refreshView = findViewById(R.id.swipe_refresh);
    refreshView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override
      public void onRefresh() {
        dashboardPresenter.refresh();
      }
    });

    FloatingActionButton addRequestFloatingButton = findViewById(R.id.add_crop_request);
    addRequestFloatingButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent addCropRequestIntent = new Intent
          (DashboardActivity.this, MakeCropRequestActivity.class);
        DashboardActivity.this.startActivityForResult(addCropRequestIntent, CREATE_CROP_REQUEST_CODE);
      }
    });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_dashboard, menu);
    return true;
  }

  @Override
  public void onRefreshDone() {
    refreshView.setRefreshing(false);
  }

  @Override
  public void showProgressDialog() {
    progressDialog = new ProgressDialog(DashboardActivity.this);
    progressDialog.setTitle("Loading Requests");
    progressDialog.setMessage("Please Wait...");
    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    progressDialog.setCancelable(false);
    progressDialog.show();
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.signOut:
        Intent signOutIntent = new Intent(DashboardActivity.this, LoginActivity.class);
        DashboardActivity.this.startActivity(signOutIntent);
        finish();
        Toast.makeText(this, "sign out", Toast.LENGTH_LONG).show();
        return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public void showCropRequests(List<CropRequest> cropRequests) {
    progressDialog.dismiss();
    CropRequestAdapter cropRequestAdapter =
      new CropRequestAdapter(DashboardActivity.this, cropRequests);
    ListView cropRequestView = findViewById(R.id.crop_request_view);
    cropRequestView.setAdapter(cropRequestAdapter);
  }

  @Override
  public void showError() {

  }
}
