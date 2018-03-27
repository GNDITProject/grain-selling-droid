package com.prayasj.gndit.presenter;

import android.content.Context;

import com.prayasj.gndit.SaveSharedPreferences;
import com.prayasj.gndit.model.UserInfo;
import com.prayasj.gndit.network.JwtTokenHolder;
import com.prayasj.gndit.network.response.LoginResponse;
import com.prayasj.gndit.network.service.LoginService;
import com.prayasj.gndit.validator.UserInfoValidator;
import com.prayasj.gndit.views.LoginView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter {
  private  SaveSharedPreferences saveSharedPreferences;
  private final LoginService loginService;
  private final LoginView loginView;
  private UserInfoValidator userInfoValidator;

  public LoginPresenter(SaveSharedPreferences saveSharedPreferences, LoginService loginService, LoginView loginView, UserInfoValidator userInfoValidator) {
    this.saveSharedPreferences = saveSharedPreferences;
    this.loginService = loginService;
    this.loginView = loginView;
    this.userInfoValidator = userInfoValidator;
  }

  public void login(String username, String password) {
    loginView.showProgressLoader();
    UserInfo currentUser = new UserInfo(username, password);
    if (userInfoValidator.isUserInfoValid(currentUser) == null) {
      makeRequest(currentUser);
    } else {
      loginView.showErrorMessage(userInfoValidator.isUserInfoValid(currentUser));
    }
  }

  private void makeRequest(final UserInfo currentUser) {
    loginService.login(currentUser).enqueue(new Callback<LoginResponse>() {
      @Override
      public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
        if (!response.isSuccessful()) {
          loginView.onLoginFailure();
          return;
        }
        saveCredentialsForAutoLogin(currentUser);
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

  private void saveCredentialsForAutoLogin(UserInfo currentUser) {
    saveSharedPreferences.setPrefUserName(currentUser.getUsername());
    saveSharedPreferences.setPrefUserPassword(currentUser.getPassword());
  }
}
