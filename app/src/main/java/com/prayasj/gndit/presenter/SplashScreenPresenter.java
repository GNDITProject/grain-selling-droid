package com.prayasj.gndit.presenter;

import com.prayasj.gndit.AutoLoginManager;
import com.prayasj.gndit.SaveSharedPreferences;
import com.prayasj.gndit.views.SplashScreenView;

public class SplashScreenPresenter {
  private SplashScreenView splashScreenView;
  private AutoLoginManager autoLoginManager;
  private SaveSharedPreferences saveSharedPreferences;

  public SplashScreenPresenter(SplashScreenView splashScreenView,
                               AutoLoginManager autoLoginManager,
                               SaveSharedPreferences saveSharedPreferences) {
    this.splashScreenView = splashScreenView;
    this.autoLoginManager = autoLoginManager;
    this.saveSharedPreferences = saveSharedPreferences;
  }

  public void doLoginIfRequired() {
    if (saveSharedPreferences.getPrefUserName() != null) {
      autoLoginManager.loginWithSavedCredentials();
      return;
    }
    splashScreenView.openSignupActivity();
  }
}
