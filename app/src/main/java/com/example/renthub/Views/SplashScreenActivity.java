package com.example.renthub.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.app.ActivityOptions;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
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
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class SplashScreenActivity extends AppCompatActivity {
    FusedLocationProviderClient fusedLocationProviderClient;
    public static String lattitude;
    public static String longitude;
    public static String address;
    public static String city;
    public static String country;
    private final static int REQUEST_CODE = 100;



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


       // coordination
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

    }
    @Override
    public void onStart() {
        super.onStart();
        getLastLocation();
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
    private void getLastLocation(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){


            fusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {
                                try {
                                    Geocoder geocoder = new Geocoder(SplashScreenActivity.this, Locale.getDefault());
                                    List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

                                    if (addresses != null && addresses.size() > 0) {
                                        lattitude = String.valueOf(addresses.get(0).getLatitude());
                                        longitude = String.valueOf(addresses.get(0).getLongitude());
                                        address = String.valueOf(addresses.get(0).getAddressLine(0));
                                        city = String.valueOf(addresses.get(0).getLocality());
                                        country = String.valueOf(addresses.get(0).getCountryName());

                                        // Log the location details
                                        Log.d("TAG", "Location: " + lattitude + ", " + longitude);
                                        Log.d("TAG", "Address: " + address + ", " + city + ", " + country);
                                    } else {
                                        Log.d("TAG", "No address found");
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                    Log.e("TAG", "IOException while getting location: " + e.getMessage());
                                }
                            } else {
                                Log.d("TAG", "Location is null");
                            }
                        }
                    })
                    .addOnFailureListener(this, new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e("TAG", "Error getting last location: " + e.getMessage());
                        }
                    });


    }


    else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @org.jetbrains.annotations.NotNull String[] permissions, @NonNull @org.jetbrains.annotations.NotNull int[] grantResults) {

        if (requestCode == REQUEST_CODE){

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){


                getLastLocation();

            }

        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}   