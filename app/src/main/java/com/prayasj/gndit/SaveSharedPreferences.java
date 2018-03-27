package com.prayasj.gndit;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class SaveSharedPreferences {
  private String PREF_USER_NAME = "username";
  private String PREF_USER_PASSWORD = "password";
  private Context context;

  public SaveSharedPreferences (Context context){
    this.context = context;
  }

  private SharedPreferences getSharedPreferences(Context ctx) {
    return PreferenceManager.getDefaultSharedPreferences(ctx);
  }

  public String getPrefUserName(){
    try {
      return SecurityManager.decrypt(getSharedPreferences(context).getString(PREF_USER_NAME, ""));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public String getPrefUserPassword(){
    try {
      return SecurityManager.decrypt(getSharedPreferences(context).getString(PREF_USER_PASSWORD, ""));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public void setPrefUserName(String userName){
    Editor editor = getSharedPreferences(context).edit();
    try {
      editor.putString(PREF_USER_NAME, SecurityManager.encrypt(userName));
    } catch (Exception e) {
      e.printStackTrace();
    }
    editor.apply();
  }

  public void setPrefUserPassword(String password){
    Editor editor = getSharedPreferences(context).edit();
    try {
      editor.putString(PREF_USER_PASSWORD, SecurityManager.encrypt(password));
    } catch (Exception e) {
      e.printStackTrace();
    }
    editor.apply();
  }
}
