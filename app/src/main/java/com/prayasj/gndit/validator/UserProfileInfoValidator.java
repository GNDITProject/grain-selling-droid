package com.prayasj.gndit.validator;


import com.prayasj.gndit.model.UserProfileInfo;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

public class UserProfileInfoValidator {

  private static final String PATTERN_FOR_NAME = "^[A-Za-z-, ]{3,20}$";

  private static final String PATTERN_FOR_ADDRESS = "^.{10,200}$";

  private static final String PATTERN_FOR_GENDER = "^[MF]$";

  private static final String PATTERN_FOR_CONTACT = "^[0-9]{5,16}$";

  public String isUserProfileInfoValid(UserProfileInfo userProfileInfo) {


    if (!matchesPattern(PATTERN_FOR_NAME, userProfileInfo.getName())) {
      return "Name is not valid";
    } else if (!matchesPattern(PATTERN_FOR_ADDRESS, userProfileInfo.getAddress())) {
      return "Address is not valid";
    } else if (!matchesPattern(PATTERN_FOR_GENDER, userProfileInfo.getGender())) {
      return "Gender is not valid";
    } else if (!matchesPattern(PATTERN_FOR_CONTACT, userProfileInfo.getContact())) {
      return "Enter Valid Contact Number";
    } else if (isDateValid(userProfileInfo)) {
      return "Enter Valid Date";
    } else
      return null;
  }


  private boolean isDateValid(UserProfileInfo userProfileInfo) {
    Date date = new Date(userProfileInfo.getDob());
    Calendar selectedDate = Calendar.getInstance();
    selectedDate.setTime(date);

    Calendar before18Years = Calendar.getInstance();
    before18Years.add(Calendar.YEAR, -18);
    return selectedDate.compareTo(before18Years) > 0;
  }

  private boolean matchesPattern(String pattern, String text) {
    return Pattern.compile(pattern).matcher(text).matches();
  }
}
