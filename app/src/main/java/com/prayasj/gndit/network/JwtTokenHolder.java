package com.prayasj.gndit.network;

public class JwtTokenHolder {
  private static JwtTokenHolder holder;
  private String jwtToken;

  private JwtTokenHolder() {
  }

  public void setToken(String token) {
    jwtToken = token;
  }

  public String getToken() {
    return jwtToken;
  }

  public boolean hasToken(){
    return jwtToken != null;
  }

  public static JwtTokenHolder getInstance() {
    if (holder == null) {
      holder = new JwtTokenHolder();
    }
    return holder;
  }
}
