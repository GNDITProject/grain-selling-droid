package com.prayasj.gndit.presenter;

import android.content.Context;

import com.prayasj.gndit.SaveSharedPreferences;
import com.prayasj.gndit.activity.LoginActivity;
import com.prayasj.gndit.model.UserInfo;
import com.prayasj.gndit.network.JwtTokenHolder;
import com.prayasj.gndit.network.response.LoginResponse;
import com.prayasj.gndit.network.service.LoginService;
import com.prayasj.gndit.validator.UserInfoValidator;
import com.prayasj.gndit.validator.UserProfileInfoValidator;
import com.prayasj.gndit.views.LoginView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter {
  private final LoginService loginService;
  private final LoginView loginView;
  private UserInfoValidator userInfoValidator;
  private Context context;

  public LoginPresenter(Context context, LoginService loginService, LoginView loginView, UserInfoValidator userInfoValidator) {
    this.context = context;
    this.loginService = loginService;
    this.loginView = loginView;
    this.userInfoValidator = userInfoValidator;
  }

  public void loginWithSavedCredentials() {
    loginView.showProgressLoader();
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

  public void login(String username, String password) {
    loginView.showProgressLoader();
    userInfoValidator = new UserInfoValidator();
    UserInfo currentUser = new UserInfo(username, password);
    if (new UserInfoValidator().isUserInfoValid(currentUser) == null) {
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
    SaveSharedPreferences.setPrefUserName(context, currentUser.getUsername());
    SaveSharedPreferences.setPrefUserPassword(context, currentUser.getPassword());
  }
}
