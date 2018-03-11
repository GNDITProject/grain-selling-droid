package com.prayasj.gndit.presenter;

public interface LoginView {
  void showProgressLoader();

  void navigateToDashboardActivity();

  void navigateToCreateProfileActivity();

  void onLoginFailure();
}
