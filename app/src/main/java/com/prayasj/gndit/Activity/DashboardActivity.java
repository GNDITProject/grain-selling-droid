package com.prayasj.gndit.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.prayasj.gndit.presenter.DashboardView;

import java.util.List;


public class DashboardActivity extends AppCompatActivity implements DashboardView {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_dashboard);
    DashboardPresenter dashboardPresenter = new DashboardPresenter(ServiceBuilder.build(CropRequestService.class),this);
    dashboardPresenter.getCropRequests();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_dashboard, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()){
      case R.id.signOut:
        Intent signOutIntent = new Intent(DashboardActivity.this, LoginActivity.class);
        DashboardActivity.this.startActivity(signOutIntent);
        finish();
        Toast.makeText(this,"sign out",Toast.LENGTH_LONG).show();
        return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public void showCropRequests(List<CropRequest> cropRequests) {
    CropRequestAdapter cropRequestAdapter =
      new CropRequestAdapter(DashboardActivity.this, cropRequests);
    ListView cropRequestView = findViewById(R.id.crop_request_view);
    cropRequestView.setAdapter(cropRequestAdapter);
  }

  @Override
  public void showError() {

  }
}
