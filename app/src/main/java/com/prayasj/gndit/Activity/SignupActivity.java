package com.prayasj.gndit.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.prayasj.gndit.R;
import com.prayasj.gndit.custom.views.EditText;
import com.prayasj.gndit.network.ServiceBuilder;
import com.prayasj.gndit.network.service.SignUpService;
import com.prayasj.gndit.presenter.SignUpPresenter;
import com.prayasj.gndit.presenter.SignUpView;

public class SignupActivity extends AppCompatActivity implements SignUpView {

  public static final String NEW_USER_NAME = "userName";
  private ProgressDialog progressDialog;
  private SignUpPresenter signUpPresenter;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_signup);

    View submitButton = findViewById(R.id.submitButton);
    submitButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        signup();
      }
    });

    TextView loginHere = findViewById(R.id.login2);
    loginHere.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent loginIntent = new Intent(SignupActivity.this, LoginActivity.class);
        SignupActivity.this.startActivity(loginIntent);
        finish();
      }
    });
    signUpPresenter = new SignUpPresenter(ServiceBuilder.build(SignUpService.class), this);
  }

  private void signup() {
    final String username = this.<EditText>findViewById(R.id.username).getText().toString();
    final String password = this.<EditText>findViewById(R.id.password).getText().toString();
    signUpPresenter.signup(username, password);
  }

  @Override
  public void showProgressLoader() {
    progressDialog = new ProgressDialog(SignupActivity.this);
    progressDialog.setTitle("SignUp Progress");
    progressDialog.setMessage("Loading...");
    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    progressDialog.setCancelable(false);
    progressDialog.show();
  }

  @Override
  public void onSignUpSuccessful() {
    progressDialog.dismiss();
    Toast.makeText(SignupActivity.this, "Signup successful",
      Toast.LENGTH_SHORT).show();
    Intent loginIntent = new Intent(SignupActivity.this, LoginActivity.class).
      putExtra(NEW_USER_NAME, this.<EditText>findViewById(R.id.password).getText().toString());
    SignupActivity.this.startActivity(loginIntent);
  }

  @Override
  public void onSignUpFailure() {
    progressDialog.dismiss();
    Toast.makeText(SignupActivity.this, "Signup failure", Toast.LENGTH_SHORT).show();
  }
}
