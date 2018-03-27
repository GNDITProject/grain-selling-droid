package com.prayasj.gndit;

import android.content.Context;

import com.prayasj.gndit.model.UserInfo;
import com.prayasj.gndit.network.JwtTokenHolder;
import com.prayasj.gndit.network.response.LoginResponse;
import com.prayasj.gndit.network.service.LoginService;
import com.prayasj.gndit.views.LoginView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AutoLoginManager {
  private final LoginService loginService;
  private final LoginView loginView;
  private final Context context;

  public AutoLoginManager(LoginService loginService, LoginView loginView, Context context) {
    this.loginService = loginService;
    this.loginView = loginView;
    this.context = context;
  }

  public void loginWithSavedCredentials() {
    UserInfo user = new UserInfo
      (SaveSharedPreferences.getUserName(context), SaveSharedPreferences.getPrefUserPassword(context));
    loginService.login(user).enqueue(new Callback<LoginResponse>() {
      @Override
      public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
        if (!response.isSuccessful()) {
          loginView.onLoginFailure();
          return;
        }
        String jwtToken = response.headers().get("jwt_token");
        JwtTokenHolder.getInstance().setToken(jwtToken);
        if (response.body().hasProfile()) {
          loginView.navigateToDashboardActivity();
        } else {
          loginView.navigateToCreateProfileActivity();
        }
      }

      @Override
      public void onFailure(Call<LoginResponse> call, Throwable t) {
        loginView.onLoginFailure();
      }
    });
  }
}
