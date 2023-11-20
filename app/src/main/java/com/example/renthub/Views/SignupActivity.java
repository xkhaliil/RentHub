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

public class SignupActivity extends AppCompatActivity {
    LottieAnimationView lottieanimation;
    TextView Welcometext,sloganwelcome;
    TextInputLayout username,password,phone,email;
    Button callLogin,signup_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN, android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN); // to hide the status bar
        setContentView(R.layout.activity_signup);
        lottieanimation = findViewById(R.id.lottieAnimationView);
        Welcometext = findViewById(R.id.welcome);
        sloganwelcome = findViewById(R.id.continuee);
        username = findViewById(R.id.fullname);
        password = findViewById(R.id.password);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        signup_btn = findViewById(R.id.buttongo);
        callLogin = findViewById(R.id.buttonlogin);
        callLogin.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                android.content.Intent intent = new android.content.Intent(SignupActivity.this, LoginActivity.class);
                Pair[] pairs = new Pair[8];
                pairs[0] = new Pair<android.view.View, String>(findViewById(R.id.lottieAnimationView), "lottie_img");
                pairs[1] = new Pair<android.view.View, String>(findViewById(R.id.welcome), "text_img");
                pairs[2] = new Pair<android.view.View, String>(findViewById(R.id.continuee), "text2_img");
                pairs[3] = new Pair<android.view.View, String>(findViewById(R.id.fullname), "input1_img");
                pairs[4] = new Pair<android.view.View, String>(findViewById(R.id.password), "input2_img");
                pairs[5] = new Pair<android.view.View, String>(findViewById(R.id.phone), "input3_img");
                pairs[6] = new Pair<android.view.View, String>(findViewById(R.id.email), "input4_img");
                pairs[7] = new Pair<android.view.View, String>(findViewById(R.id.buttongo), "button1_img");
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignupActivity.this, pairs);
                startActivity(intent, options.toBundle());
            }
        });
    }
}