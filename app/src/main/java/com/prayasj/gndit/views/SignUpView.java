package com.prayasj.gndit.views;


public interface SignUpView {
  void showProgressLoader();

  void onSignUpSuccessful();

  void onSignUpFailure();

  void showErrorMessage(String userInfoValid);
}
