package com.prayasj.gndit;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class SaveSharedPreferences {
  static String PREF_USER_NAME = "username";
  static String PREF_USER_PASSWORD = "password";

  static SharedPreferences getSharedPreferences(Context ctx) {
    return PreferenceManager.getDefaultSharedPreferences(ctx);
  }

  public static String getUserName(Context context){
    try {
      return SecurityManager.decrypt(getSharedPreferences(context).getString(PREF_USER_NAME, ""));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public static String getPrefUserPassword(Context context){
    try {
      return SecurityManager.decrypt(getSharedPreferences(context).getString(PREF_USER_PASSWORD, ""));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public static void setPrefUserName(Context context, String userName){
    Editor editor = getSharedPreferences(context).edit();
    try {
      editor.putString(PREF_USER_NAME, SecurityManager.encrypt(userName));
    } catch (Exception e) {
      e.printStackTrace();
    }
    editor.apply();
  }

  public static void setPrefUserPassword(Context context, String password){
    Editor editor = getSharedPreferences(context).edit();
    try {
      editor.putString(PREF_USER_PASSWORD, SecurityManager.encrypt(password));
    } catch (Exception e) {
      e.printStackTrace();
    }
    editor.apply();
  }
}
