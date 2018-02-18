package com.prayasj.gndit;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

      View signUpHere = findViewById(R.id.signup_here);
      signUpHere.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          Intent signUpIntent = new Intent(LoginActivity.this, SignupActivity.class);
          LoginActivity.this.startActivity(signUpIntent);
        }
      });
    }
}
