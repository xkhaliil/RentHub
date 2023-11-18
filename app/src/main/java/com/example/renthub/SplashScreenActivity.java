package com.example.renthub;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class SplashScreenActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 5000; // 5 seconds

    // variables
    Animation topanim, bottomanim;
    LottieAnimationView car;
    ImageView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); // to hide the status bar
        setContentView(R.layout.activity_splashscreen);

        //annimation
        topanim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomanim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        //hooks
        car = findViewById(R.id.lottieAnimationView);
        logo = findViewById(R.id.splashScreenLogo);

        car.setAnimation(topanim);
        logo.setAnimation(bottomanim);

        // to start the main activity after the splash screen
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                android.content.Intent intent = new android.content.Intent(SplashScreenActivity.this, DashboardActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN);

    }
}   