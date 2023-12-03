package com.example.renthub.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.renthub.R;

import com.google.firebase.Firebase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RentCarActivity extends AppCompatActivity {
ImageView imageView;
CalendarView calendarView,calendarView2;
Spinner spinner;
TextView price;
Button confirm;
public static String selectedDate1;
public static String selectedDate2;
public static String pricee;
    private boolean isDate1Selected = false;
    private boolean isDate2Selected = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_car);
        getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN, android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN); // to hide the status bar
        imageView = findViewById(R.id.imageView2);
        calendarView = findViewById(R.id.calendarView3);
        calendarView2 = findViewById(R.id.calendarView4);
        spinner = findViewById(R.id.spinner);
        price = findViewById(R.id.price);
        confirm = findViewById(R.id.confirm);
        Intent intent = getIntent();
        String uid = intent.getStringExtra("DocumentUID");
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        System.out.println(uid);
        DocumentReference docRef = db.collection("cars").document(uid);
        docRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    pricee = document.getString("price");
                    price.setText(pricee);

                } else {
                    System.out.println("No such document");
                }
            } else {
                System.out.println("get failed with " + task.getException());
            }
        });
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                selectedDate1 = String.format("%02d-%02d-%04d %02d:%02d:%02d", dayOfMonth, month + 1, year, 23, 59, 59);
                Toast.makeText(RentCarActivity.this, "Selected Date 1: " + selectedDate1, Toast.LENGTH_SHORT).show();
                isDate1Selected = true;
            }
        });

        calendarView2.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                selectedDate2 = String.format("%02d-%02d-%04d %02d:%02d:%02d", dayOfMonth, month + 1, year, 23, 59, 59);
                Toast.makeText(RentCarActivity.this, "Selected Date 2: " + selectedDate2, Toast.LENGTH_SHORT).show();
                isDate2Selected = true;
            }
        });

        confirm.setOnClickListener(v -> {
            if (isDate1Selected && isDate2Selected) {
                AlertDialog.Builder builder = new AlertDialog.Builder(RentCarActivity.this);
                builder.setTitle("Confirm Payment");
                builder.setMessage("Are you sure you want to proceed with the payment?");


                builder.setPositiveButton("Yes", (dialog, which) -> {
                    // User clicked Yes button
                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                        Date date1 = sdf.parse(selectedDate1);
                        Date date2 = sdf.parse(selectedDate2);
                        long difference_In_Time = date2.getTime() - date1.getTime();
                        long difference_In_Days = (difference_In_Time / (1000 * 60 * 60 * 24)) % 365;
                        int pricee = Integer.parseInt(price.getText().toString());
                        int total = (int) (difference_In_Days * pricee);
                        // Proceed to the next activity for payment, passing the total amount
                        Intent paymentIntent = new Intent(RentCarActivity.this, PaymentActivity.class);
                        paymentIntent.putExtra("totalAmount", total);
                        startActivity(paymentIntent);

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                });

                builder.setNegativeButton("No", (dialog, which) -> {
                    // User clicked No button
                    dialog.dismiss();
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            } else {
                Toast.makeText(RentCarActivity.this, "Please select both dates", Toast.LENGTH_SHORT).show();
            }
        });

    }

}