package com.prayasj.gndit.presenter;

import com.prayasj.gndit.model.UserInfo;
import com.prayasj.gndit.network.JwtTokenHolder;
import com.prayasj.gndit.network.response.LoginResponse;
import com.prayasj.gndit.network.service.LoginService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter {
  private final LoginService loginService;
  private final LoginView loginView;

  public LoginPresenter(LoginService loginService, LoginView loginView) {
    this.loginService = loginService;
    this.loginView = loginView;
  }

  public void login(String username, String password) {
    loginView.showProgressLoader();
    loginService.login(new UserInfo(username, password)).enqueue(new Callback<LoginResponse>() {
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
      public void onFailure(Call<LoginResponse> call, Throwable throwable) {
        loginView.onLoginFailure();
      }
    });
  }
}
