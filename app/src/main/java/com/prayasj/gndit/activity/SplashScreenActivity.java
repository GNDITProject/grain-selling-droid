package com.prayasj.gndit.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.prayasj.gndit.AutoLoginManager;
import com.prayasj.gndit.R;
import com.prayasj.gndit.SaveSharedPreferences;
import com.prayasj.gndit.network.ServiceBuilder;
import com.prayasj.gndit.network.service.LoginService;
import com.prayasj.gndit.presenter.SplashScreenPresenter;
import com.prayasj.gndit.views.LoginView;
import com.prayasj.gndit.views.SplashScreenView;

public class SplashScreenActivity extends Activity implements LoginView, SplashScreenView {

  private ProgressDialog progressDialog;
  private Handler handler;
  private AutoLoginManager autoLoginManager;
  private SaveSharedPreferences saveSharedPreferences;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash_screen);

    saveSharedPreferences = new SaveSharedPreferences(this);
    autoLoginManager = new AutoLoginManager((ServiceBuilder.build(LoginService.class)), this, saveSharedPreferences);
    SplashScreenPresenter splashScreenPresenter = new SplashScreenPresenter(this, autoLoginManager, saveSharedPreferences);
    splashScreenPresenter.doLoginIfRequired();

  }

  @Override
  public void showProgressLoader() {
    progressDialog = new ProgressDialog(SplashScreenActivity.this);
    progressDialog.setTitle("Login Progress");
    progressDialog.setMessage("Loading...");
    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    progressDialog.setCancelable(false);
    progressDialog.show();
  }

  @Override
  public void navigateToDashboardActivity() {
    Intent dashboardIntent = new Intent(SplashScreenActivity.this, DashboardActivity.class);
    SplashScreenActivity.this.startActivity(dashboardIntent);
    finish();

  }

  @Override
  public void navigateToCreateProfileActivity() {
    Intent dashboardIntent = new Intent(SplashScreenActivity.this, CreateProfileActivity.class);
    SplashScreenActivity.this.startActivity(dashboardIntent);
    finish();
  }

  @Override
  public void onLoginFailure() {
    showSnackBar("Please check your internet connection");
  }

  @Override
  public void showErrorMessage(String message) {
    progressDialog.dismiss();
  }

  private void showSnackBar(String message) {
    final Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT);
    snackbar.setAction("OK", new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        snackbar.dismiss();
      }
    }).show();
  }

  @Override
  public void openSignupActivity() {
    handler = new Handler();
    handler.postDelayed(new Runnable() {
      @Override
      public void run() {
        Intent signupintent = new Intent(SplashScreenActivity.this, SignupActivity.class);
        SplashScreenActivity.this.startActivity(signupintent);
        finish();
      }
    }, 3000);
  }
}
