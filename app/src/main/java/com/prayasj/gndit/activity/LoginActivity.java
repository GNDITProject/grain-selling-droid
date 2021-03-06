package com.prayasj.gndit.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.prayasj.gndit.R;
import com.prayasj.gndit.SaveSharedPreferences;
import com.prayasj.gndit.custom.views.EditText;
import com.prayasj.gndit.network.ServiceBuilder;
import com.prayasj.gndit.network.service.LoginService;
import com.prayasj.gndit.presenter.LoginPresenter;
import com.prayasj.gndit.validator.UserInfoValidator;
import com.prayasj.gndit.views.LoginView;


import static com.prayasj.gndit.activity.SignupActivity.NEW_USER_NAME;

public class LoginActivity extends AppCompatActivity implements LoginView {

  private ProgressDialog progressDialog;
  private LoginPresenter loginPresenter;
  private UserInfoValidator userInfoValidator;
  private SaveSharedPreferences saveSharedPreferences;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    EditText loginUsername = findViewById(R.id.loginUsername);
    loginUsername.setText(getIntent().getStringExtra(NEW_USER_NAME));


    View loginButton = findViewById(R.id.loginButton);
    loginButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        login();
      }
    });

    View signUpHere = findViewById(R.id.register2);
    signUpHere.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent signUpIntent = new Intent(LoginActivity.this, SignupActivity.class);
        LoginActivity.this.startActivity(signUpIntent);
        finish();
      }
    });
    userInfoValidator = new UserInfoValidator();
    saveSharedPreferences = new SaveSharedPreferences(this);
    loginPresenter = new LoginPresenter(saveSharedPreferences, (ServiceBuilder.build(LoginService.class)), this, userInfoValidator);
  }

  private void login() {
    final String username = this.<EditText>findViewById(R.id.loginUsername).getText().toString();
    final String password = this.<EditText>findViewById(R.id.loginPassword).getText().toString();
    loginPresenter.login(username, password);
  }

  @Override
  public void showProgressLoader() {
    progressDialog = new ProgressDialog(LoginActivity.this);
    progressDialog.setTitle("Login Progress");
    progressDialog.setMessage("Loading...");
    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    progressDialog.setCancelable(false);
    progressDialog.show();
  }

  @Override
  public void navigateToDashboardActivity() {
    progressDialog.dismiss();
    Intent dashboardIntent = new Intent(LoginActivity.this, DashboardActivity.class);
    LoginActivity.this.startActivity(dashboardIntent);
    finish();
  }

  @Override
  public void navigateToCreateProfileActivity() {
    progressDialog.dismiss();
    Intent dashboardIntent = new Intent(LoginActivity.this, CreateProfileActivity.class);
    LoginActivity.this.startActivity(dashboardIntent);
    finish();
  }

  @Override
  public void onLoginFailure() {
    progressDialog.dismiss();
    Toast.makeText(LoginActivity.this, "Login failure", Toast.LENGTH_SHORT).show();

  }

  @Override
  public void showErrorMessage(String message) {
    progressDialog.dismiss();
    showSnackBar(message);
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
}
