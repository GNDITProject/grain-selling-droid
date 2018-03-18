package com.prayasj.gndit.validator;


import com.prayasj.gndit.model.UserInfo;

import java.util.regex.Pattern;

public class UserInfoValidator {

  private static final String PATTERN_FOR_USERNAME = "^[A-Za-z0-9_]{3,10}$";
  private static final String PATTERN_FOR_PASSWORD = "^.{6,20}$";

  public String isUserInfoValid(UserInfo userInfo) {
    if (!matchesPattern(PATTERN_FOR_USERNAME, userInfo.getUsername())) {
      return "Username is not valid";
    } else if (!matchesPattern(PATTERN_FOR_PASSWORD, userInfo.getPassword())) {
      return "Password is not valid";
    } else
      return null;
  }

  private boolean matchesPattern(String pattern, String text) {
    return Pattern.compile(pattern).matcher(text).matches();
  }
}
