package com.prayasj.gndit.presenter;

import com.google.gson.Gson;
import com.prayasj.gndit.model.UserProfileInfo;
import com.prayasj.gndit.network.response.ErrorResponse;
import com.prayasj.gndit.network.service.UserProfileService;
import com.prayasj.gndit.validator.UserProfileInfoValidator;
import com.prayasj.gndit.views.CreateUserProfileView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateProfilePresenter {
  private UserProfileService userProfileService;
  private CreateUserProfileView view;
  private UserProfileInfoValidator userProfileInfoValidator;

  public CreateProfilePresenter(UserProfileService userProfileService, CreateUserProfileView view,
                                UserProfileInfoValidator userProfileInfoValidator) {
    this.userProfileService = userProfileService;
    this.view = view;
    this.userProfileInfoValidator = userProfileInfoValidator;

  }

  public void saveUserProfile() {
    view.showProgressDialog();
    UserProfileInfo userProfileInfo = view.getUserProfileInfo();
    String message = userProfileInfoValidator.isUserProfileInfoValid(userProfileInfo);
    if (message == null) {
      makeRequest(userProfileInfo);
    } else
      view.showErrorMessage(message);
  }

  private void makeRequest(UserProfileInfo userProfileInfo) {
    userProfileService.saveProfile(userProfileInfo).enqueue(new Callback<Void>() {
      @Override
      public void onResponse(Call<Void> call, Response<Void> response) {
        if (response.isSuccessful()) {
          view.onProfileSaveSuccessful();
          return;
        }
        if (response.errorBody() != null) {
          ErrorResponse errorResponse = new Gson().fromJson(response.errorBody().charStream(), ErrorResponse.class);
          view.onProfileSaveFailure(errorResponse);
          return;
        }
        view.onTechnicalError();
      }

      @Override
      public void onFailure(Call<Void> call, Throwable t) {
        view.onTechnicalError();
      }
    });
  }
}
