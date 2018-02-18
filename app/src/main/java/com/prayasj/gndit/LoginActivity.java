package com.prayasj.gndit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.prayasj.gndit.custom.views.EditText;
import com.prayasj.gndit.model.UserInfo;
import com.prayasj.gndit.network.ServiceBuilder;
import com.prayasj.gndit.network.service.LoginService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.Boolean.TRUE;

public class LoginActivity extends AppCompatActivity {

  private ProgressDialog progressDialog;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    View loginButton = findViewById(R.id.loginButton);
    loginButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        login();
      }
    });

    View signUpHere = findViewById(R.id.signup_here);
    signUpHere.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent signUpIntent = new Intent(LoginActivity.this, SignupActivity.class);
        LoginActivity.this.startActivity(signUpIntent);
      }
    });
  }

  private void login() {
    progressDialog = new ProgressDialog(LoginActivity.this);
    progressDialog.setTitle("Login Progress");
    progressDialog.setMessage("Loading...");
    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    progressDialog.setCancelable(false);
    progressDialog.show();
    String username = this.<EditText>findViewById(R.id.loginUsername).getText().toString();
    String password = this.<EditText>findViewById(R.id.loginPassword).getText().toString();
    UserInfo userInfo = new UserInfo(username, password);
    LoginService loginService = ServiceBuilder.build(LoginService.class);
    loginService.login(userInfo).enqueue(new Callback<Void>() {
      @Override
      public void onResponse(Call<Void> call, Response<Void> response) {
        if (response.isSuccessful()) {
          progressDialog.dismiss();
          String jwt_token = response.headers().get("jwt_token");
          Toast.makeText(LoginActivity.this, "Signup successful: " + jwt_token,
            Toast.LENGTH_SHORT).show();
        } else {
          progressDialog.dismiss();
          Toast.makeText(LoginActivity.this, "Signup failure", Toast.LENGTH_SHORT).show();
        }
      }

      @Override
      public void onFailure(Call<Void> call, Throwable throwable) {
        progressDialog.dismiss();
        Toast.makeText(LoginActivity.this, "Signup failure", Toast.LENGTH_SHORT).show();
      }
    });
  }
}
