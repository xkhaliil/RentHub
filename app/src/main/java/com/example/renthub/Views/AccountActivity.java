package com.example.renthub.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.renthub.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AccountActivity extends AppCompatActivity {
    TextView CurrentLocation;
    RelativeLayout assistant;
    TextInputLayout username;
    TextInputLayout email;
    TextInputLayout phone;
    TextInputLayout password;
    Button changePassword;
    ImageButton signOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        super.onCreate(savedInstanceState);
        getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN, android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN); // to hide the status bar
        setContentView(R.layout.activity_account);
        CurrentLocation = findViewById(R.id.currentlocation);
        assistant = findViewById(R.id.assistant);
        username = findViewById(R.id.usernameaccount);
        email = findViewById(R.id.emailAccount);
        phone = findViewById(R.id.phoneaccount);
        password = findViewById(R.id.passwordaccount);
        Button changePassword = findViewById(R.id.changepasswordbutton);
        ImageButton signOut = findViewById(R.id.signoutbutton);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String name = user.getDisplayName();
            String email1 = user.getEmail();
            String phone1 = user.getPhoneNumber();
            email.getEditText().setText(email1);







        }
    }
}