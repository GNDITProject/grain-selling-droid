package com.prayasj.gndit.presenter;

import com.prayasj.gndit.model.UserInfo;
import com.prayasj.gndit.network.service.SignUpService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpPresenter {

  private SignUpService signUpService;
  private SignUpView signUpView;

  public SignUpPresenter(SignUpService signUpService, SignUpView signUpView) {
    this.signUpService = signUpService;
    this.signUpView = signUpView;
  }

  public void signup(String username, String password) {
    signUpView.showProgressLoader();
    signUpService.signup(new UserInfo(username, password)).enqueue(new Callback<Void>() {
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
