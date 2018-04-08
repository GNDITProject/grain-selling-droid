package com.prayasj.gndit.views;

import com.prayasj.gndit.model.UserProfileInfo;
import com.prayasj.gndit.network.response.ErrorResponse;

public interface CreateUserProfileView {
  void showProgressDialog();

  UserProfileInfo getUserProfileInfo();

  void onProfileSaveSuccessful();

  void onProfileSaveFailure(ErrorResponse response);

  void onTechnicalError();

  void showErrorMessage(String message);
}

