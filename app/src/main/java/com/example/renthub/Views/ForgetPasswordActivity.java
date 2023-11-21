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

public class ForgetPasswordActivity extends AppCompatActivity {
    LottieAnimationView lottieAnimationView;
    TextView forget;
    TextView enteremail;
    TextInputLayout email;
    Button reset;
    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN, android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN); // to hide the status bar
        setContentView(R.layout.activity_forget_password);
        lottieAnimationView = findViewById(R.id.lottieAnimationView);
        forget = findViewById(R.id.forget);
        enteremail = findViewById(R.id.enteremailtext);
        email = findViewById(R.id.emailverification);
        reset = findViewById(R.id.reset);


        back.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                android.content.Intent intent = new android.content.Intent(ForgetPasswordActivity.this, LoginActivity.class);
                Pair[] pairs = new Pair[4];
                pairs[0] = new Pair<android.view.View, String>(findViewById(R.id.lottieAnimationView), "lottie_img");
                pairs[1] = new Pair<android.view.View, String>(findViewById(R.id.welcome), "text_img");
                pairs[2] = new Pair<android.view.View, String>(findViewById(R.id.continueee), "text2_img");
                pairs[3] = new Pair<android.view.View, String>(findViewById(R.id.username), "input1_img");
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(ForgetPasswordActivity.this, pairs);
                startActivity(intent, options.toBundle());

            }
        });
    }
}