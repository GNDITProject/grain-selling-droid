package com.prayasj.gndit;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.prayasj.gndit.custom.views.EditText;
import com.prayasj.gndit.model.UserInfo;
import com.prayasj.gndit.network.ServiceBuilder;
import com.prayasj.gndit.network.service.SignUpService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {
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

        TextView loginHere = findViewById(R.id.loginhere);
        loginHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(SignupActivity.this, LoginActivity.class);
                SignupActivity.this.startActivity(loginIntent);
            }
        });
    }

    private void signup() {
        String username = this.<EditText>findViewById(R.id.username).getText().toString();
        String password = this.<EditText>findViewById(R.id.password).getText().toString();
        UserInfo userInfo = new UserInfo(username, password);
        SignUpService signUpService = ServiceBuilder.build(SignUpService.class);
        signUpService.signup(userInfo).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(SignupActivity.this, "Signup successful", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(SignupActivity.this, "Signup failure", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
