package com.prayasj.gndit.views;

public interface LoginView {
  void showProgressLoader();

  void navigateToDashboardActivity();

  void navigateToCreateProfileActivity();

  void onLoginFailure();

  void showErrorMessage(String message);
}
