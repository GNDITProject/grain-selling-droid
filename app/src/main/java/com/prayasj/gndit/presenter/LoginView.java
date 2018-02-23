package com.prayasj.gndit.presenter;

public interface LoginView {
  void showProgressLoader();

  void onSignUpSuccessful(String token);

  void onSignUpFailure();
}
