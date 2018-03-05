package com.prayasj.gndit.presenter;

public interface LoginView {
  void showProgressLoader();

  void onLoginSuccessful();

  void onLoginFailure();
}
