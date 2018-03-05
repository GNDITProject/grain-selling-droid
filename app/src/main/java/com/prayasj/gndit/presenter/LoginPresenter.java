package com.prayasj.gndit.presenter;

import com.prayasj.gndit.model.UserInfo;
import com.prayasj.gndit.network.JwtTokenHolder;
import com.prayasj.gndit.network.service.LoginService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter {
  private final LoginService loginService;
  private final LoginView loginView;

  public LoginPresenter(LoginService loginService, LoginView loginView){
    this.loginService = loginService;
    this.loginView = loginView;
  }

  public void login(String username,String password){
    loginView.showProgressLoader();
    loginService.login(new UserInfo(username,password)).enqueue(new Callback<Void>() {
      @Override
      public void onResponse(Call<Void> call, Response<Void> response) {
        String jwt_token = response.headers().get("jwt_token");
        JwtTokenHolder.getInstance().setToken(jwt_token);
        loginView.onLoginSuccessful();
      }

      @Override
      public void onFailure(Call<Void> call, Throwable throwable) {
        loginView.onLoginFailure();
      }
    });
  }
}
