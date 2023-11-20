package com.example.renthub.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.os.Bundle;
import android.util.Pair;
import android.widget.Button;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.renthub.R;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {
Button callSignup, login_btn,forgetpassword;
TextView Welcometext,sloganwelcome;
LottieAnimationView lottieanimation;
TextInputLayout username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN, android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN); // to hide the status bar
        setContentView(R.layout.activity_login);
        lottieanimation = findViewById(R.id.lottieAnimationView);
        Welcometext = findViewById(R.id.welcome);
        sloganwelcome = findViewById(R.id.continueee);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        forgetpassword = findViewById(R.id.buttonforgetpassword);
        login_btn = findViewById(R.id.buttonlogin);
        callSignup = findViewById(R.id.buttonsignup);
        callSignup.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                android.content.Intent intent = new android.content.Intent(LoginActivity.this, SignupActivity.class);
                Pair[] pairs = new Pair[8];
                pairs[0] = new Pair<android.view.View, String>(findViewById(R.id.lottieAnimationView), "lottie_img");
                pairs[1] = new Pair<android.view.View, String>(findViewById(R.id.welcome), "text_img");
                pairs[2] = new Pair<android.view.View, String>(findViewById(R.id.continueee), "text2_img");
                pairs[3] = new Pair<android.view.View, String>(findViewById(R.id.username), "input1_img");
                pairs[4] = new Pair<android.view.View, String>(findViewById(R.id.password), "input2_img");
                pairs[5] = new Pair<android.view.View, String>(findViewById(R.id.buttonforgetpassword), "button1_img");
                pairs[6] = new Pair<android.view.View, String>(findViewById(R.id.buttonlogin), "button2_img");
                pairs[7] = new Pair<android.view.View, String>(findViewById(R.id.buttonsignup), "button3_img");
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this, pairs);
                startActivity(intent, options.toBundle());
            }
        });
        forgetpassword.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                android.content.Intent intent = new android.content.Intent(LoginActivity.this, ForgetPasswordActivity.class);

                Pair[] pairs = new Pair[6];
                pairs[0] = new Pair<android.view.View, String>(findViewById(R.id.lottieAnimationView), "lottie_img");
                pairs[1] = new Pair<android.view.View, String>(findViewById(R.id.welcome), "text_img");
                pairs[2] = new Pair<android.view.View, String>(findViewById(R.id.continueee), "text2_img");
                pairs[3] = new Pair<android.view.View, String>(findViewById(R.id.username), "input1_img");
                pairs[4] = new Pair<android.view.View, String>(findViewById(R.id.buttonlogin), "button2_img");
                pairs[5] = new Pair<android.view.View, String>(findViewById(R.id.buttonsignup), "button3_img");
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this, pairs);
                startActivity(intent, options.toBundle());

            }
        });

    }
}