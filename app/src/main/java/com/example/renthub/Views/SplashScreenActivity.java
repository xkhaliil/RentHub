package com.example.renthub.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.renthub.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreenActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private static int SPLASH_SCREEN = 5000; // 5 seconds

    // variables
    Animation topanim, bottomanim;
    LottieAnimationView car;
    ImageView logo;
    TextView slogan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); // to hide the status bar
        setContentView(R.layout.activity_splashscreen);

        //annimation
        topanim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomanim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        //hooks
        car = findViewById(R.id.lottieAnimationView);
        logo = findViewById(R.id.splashScreenLogo);
        slogan = findViewById(R.id.textView2);

        car.setAnimation(topanim);
        logo.setAnimation(bottomanim);
        slogan.setAnimation(bottomanim);


        // to start the main activity after the splash screen

    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            new android.os.Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    android.content.Intent intent = new android.content.Intent(SplashScreenActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
            }, SPLASH_SCREEN);
        }else {
            new android.os.Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    android.content.Intent intent = new android.content.Intent(SplashScreenActivity.this, LoginActivity.class);
                    Pair[] pairs = new Pair[3];
                    pairs[0] = new Pair<android.view.View, String>(car, "lottie_img");
                    pairs[1] = new Pair<android.view.View, String>(logo, "logo_img");
                    pairs[2] = new Pair<android.view.View, String>(slogan, "text_img");
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SplashScreenActivity.this, pairs);
                    startActivity(intent, options.toBundle());
                    finish();
                }
            }, SPLASH_SCREEN);
        }
    }
}   