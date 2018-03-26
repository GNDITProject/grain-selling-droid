package com.prayasj.gndit.presenter;

import android.content.Context;

import com.prayasj.gndit.AutoLoginManager;
import com.prayasj.gndit.SaveSharedPreferences;
import com.prayasj.gndit.network.ServiceBuilder;
import com.prayasj.gndit.network.service.LoginService;
import com.prayasj.gndit.views.LoginView;
import com.prayasj.gndit.views.SplashScreenView;

public class SplashScreenPresenter {
  private LoginView loginView;
  private Context context;
  private SplashScreenView splashScreenView;
  AutoLoginManager autoLoginManager;

  public SplashScreenPresenter(LoginView loginView, Context context, SplashScreenView splashScreenView) {
    this.loginView = loginView;
    this.context = context;
    this.splashScreenView = splashScreenView;
  }

  public void doLoginIfRequired() {
    if (SaveSharedPreferences.getUserName(context) != null) {
      autoLoginManager = new AutoLoginManager((ServiceBuilder.build(LoginService.class)), loginView, context);
      autoLoginManager.loginWithSavedCredentials();
      return;
    }
    splashScreenView.openSignupActivity();
  }
}
