package com.prayasj.gndit.presenter;

import com.google.gson.Gson;
import com.prayasj.gndit.model.UserProfileInfo;
import com.prayasj.gndit.network.response.ErrorResponse;
import com.prayasj.gndit.network.service.UserProfileService;
import com.prayasj.gndit.views.CreateUserProfileView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateProfilePresenter {
  private UserProfileService userProfileService;
  private CreateUserProfileView view;

  public CreateProfilePresenter(UserProfileService userProfileService, CreateUserProfileView view) {
    this.userProfileService = userProfileService;
    this.view = view;
  }

  public void saveUserProfile() {
    view.showProgressDialog();
    UserProfileInfo userProfileInfo = view.getUserProfileInfo();
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
