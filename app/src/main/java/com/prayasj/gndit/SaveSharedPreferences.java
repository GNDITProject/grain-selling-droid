package com.prayasj.gndit;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.content.SharedPreferences.Editor;

public class SaveSharedPreferences {
  static String PREF_USER_NAME = "username";
  static String PREF_USER_PASSWORD = "password";


  static SharedPreferences getSharedPreferences(Context ctx) {
    return PreferenceManager.getDefaultSharedPreferences(ctx);
  }

  public static String getUserName(Context context) {
    return getSharedPreferences(context).getString(PREF_USER_NAME, "");
  }

  public static String getPrefUserPassword(Context context) {
    return getSharedPreferences(context).getString(PREF_USER_PASSWORD, "");
  }

  public static void setPrefUserName(Context context, String userName){
    Editor editor= getSharedPreferences(context).edit();
    editor.putString(PREF_USER_NAME,userName);
    editor.commit();
  }

  public static void setPrefUserPassword(Context context,String password){
    Editor editor= getSharedPreferences(context).edit();
    editor.putString(PREF_USER_PASSWORD,password);
    editor.commit();
  }
}
