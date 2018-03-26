package com.prayasj.gndit.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.prayasj.gndit.R;
import com.prayasj.gndit.presenter.SplashScreenPresenter;
import com.prayasj.gndit.views.LoginView;
import com.prayasj.gndit.views.SplashScreenView;

public class SplashScreenActivity extends Activity implements LoginView, SplashScreenView {

  private ProgressDialog progressDialog;
  private Handler handler;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash_screen);

    SplashScreenPresenter splashScreenPresenter =
      new SplashScreenPresenter(this, SplashScreenActivity.this, this);
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
    progressDialog.dismiss();
    Intent dashboardIntent = new Intent(SplashScreenActivity.this, DashboardActivity.class);
    SplashScreenActivity.this.startActivity(dashboardIntent);
    finish();

  }

  @Override
  public void navigateToCreateProfileActivity() {
    progressDialog.dismiss();
    Intent dashboardIntent = new Intent(SplashScreenActivity.this, CreateProfileActivity.class);
    SplashScreenActivity.this.startActivity(dashboardIntent);
    finish();
  }

  @Override
  public void onLoginFailure() {
    progressDialog.dismiss();
    showSnackBar("we have some Technical Issue");
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
