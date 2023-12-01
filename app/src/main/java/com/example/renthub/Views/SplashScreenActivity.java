package com.example.renthub.Views;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.ActivityOptions;

import android.location.LocationListener;
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


import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import android.location.LocationManager;

import android.widget.Toast;


import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class SplashScreenActivity extends AppCompatActivity implements LocationListener {
    LocationManager locationManager;
    private FirebaseAuth mAuth;
    public static String lalltitude;
    public static String longtitude;
    public static String address;
    public static String area;
    public static String city;
    public static String country;


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


       // coordination
        //runtime permission
        ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},44);
        getLocation();


    }
    @Override
    public void onLocationChanged(@NonNull Location location) {
        Toast.makeText(this, ""+location.getLatitude()+"lang"+location.getLongitude(), Toast.LENGTH_SHORT).show();
        try {
            Geocoder geocoder = new Geocoder(SplashScreenActivity.this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            lalltitude = String.valueOf(addresses.get(0).getLatitude());
            longtitude = String.valueOf(addresses.get(0).getLongitude());
            address = addresses.get(0).getAddressLine(0);
            area = addresses.get(0).getLocality();
            city = addresses.get(0).getAdminArea();
            country = addresses.get(0).getCountryName();
            Log.e("address", address);
            Log.e("area", area);
            Log.e("city", city);
            Log.e("country", country);
            Log.e("lalltitude", lalltitude);
            Log.e("longtitude", longtitude);
        } catch (IOException e) {
            // Handle the exception here
            Toast.makeText(this, "Error retrieving address information: " + e.getMessage(), Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void onLocationChanged(@NonNull List<Location> locations) {
        LocationListener.super.onLocationChanged(locations);
    }

    @Override
    public void onFlushComplete(int requestCode) {
        LocationListener.super.onFlushComplete(requestCode);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        LocationListener.super.onStatusChanged(provider, status, extras);
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        LocationListener.super.onProviderEnabled(provider);
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        LocationListener.super.onProviderDisabled(provider);
    }

    public void getLocation(){
        if(ActivityCompat.checkSelfPermission(SplashScreenActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION)==getPackageManager().PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(SplashScreenActivity.this, android.Manifest.permission.ACCESS_COARSE_LOCATION)==getPackageManager().PERMISSION_GRANTED){

            locationManager=(LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,50000,5,SplashScreenActivity.this);
        }
        else{
            ActivityCompat.requestPermissions(SplashScreenActivity.this,new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},44);

        }

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
                    android.content.Intent intent = new android.content.Intent(SplashScreenActivity.this, AccountActivity.class);
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