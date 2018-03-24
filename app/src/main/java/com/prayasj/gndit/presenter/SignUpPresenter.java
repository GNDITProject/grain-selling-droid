package com.prayasj.gndit.presenter;

import com.prayasj.gndit.model.UserInfo;
import com.prayasj.gndit.network.service.SignUpService;
import com.prayasj.gndit.validator.UserInfoValidator;
import com.prayasj.gndit.views.SignUpView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpPresenter {

  private SignUpService signUpService;
  private SignUpView signUpView;
  private UserInfoValidator userInfoValidator;

  public SignUpPresenter(SignUpService signUpService, SignUpView signUpView, UserInfoValidator userInfoValidator) {
    this.signUpService = signUpService;
    this.signUpView = signUpView;
    this.userInfoValidator = userInfoValidator;
  }

  public void signup(String username, String password) {
    signUpView.showProgressLoader();
    UserInfo currentUser = new UserInfo(username, password);
    userInfoValidator =  new UserInfoValidator();
    if (userInfoValidator.isUserInfoValid(currentUser) == null){
      makeRequest(currentUser);
    }else {
      signUpView.showErrorMessage(userInfoValidator.isUserInfoValid(currentUser));
    }
  }

  private void makeRequest(UserInfo currentUser) {
    signUpService.signup(currentUser).enqueue(new Callback<Void>() {
      @Override
      public void onResponse(Call<Void> call, Response<Void> response) {
        if (response.isSuccessful()) {
          signUpView.onSignUpSuccessful();
        } else {
          signUpView.onSignUpFailure();
        }
      }

      @Override
      public void onFailure(Call<Void> call, Throwable t) {
        signUpView.onSignUpFailure();
      }
    });
  }
}
