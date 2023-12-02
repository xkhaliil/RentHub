package com.example.renthub.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.renthub.R;
import com.example.renthub.Views.DRVinterface.LoadMore;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StaticRvAdapter staticRvAdapter;
    List<DynamicRVModel> items =  new ArrayList();
    DynamicRVAdapter dynamicRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ArrayList<StaticRvModel> item = new ArrayList<>();
        item.add(new StaticRvModel(R.drawable.suv, "SUV"));
        item.add(new StaticRvModel(R.drawable.convertible, "CONVERTIBLE"));
        item.add(new StaticRvModel(R.drawable.coupe, "COUPE"));
        item.add(new StaticRvModel(R.drawable.hatchback, "HATCHBACK"));
        item.add(new StaticRvModel(R.drawable.sedan, "SEDAN"));
        item.add(new StaticRvModel(R.drawable.sport, "Sports"));
        item.add(new StaticRvModel(R.drawable.truck, "TRUCK"));
        item.add(new StaticRvModel(R.drawable.van, "VAN"));
        item.add(new StaticRvModel(R.drawable.pickup, "PICKUP"));
        item.add(new StaticRvModel(R.drawable.mini_bus, "MINI_BUS"));

      recyclerView = findViewById(R.id.rv_1);
        staticRvAdapter = new StaticRvAdapter(item);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(staticRvAdapter);



        RecyclerView drv = findViewById(R.id.rv_2);
        drv.setLayoutManager(new LinearLayoutManager(this));
        dynamicRVAdapter = new DynamicRVAdapter(drv, this, items);
        drv.setAdapter(dynamicRVAdapter);

        dynamicRVAdapter.setLoadMore(new LoadMore() {
            @Override
            public void onLoadMore() {
                if (items.size() <= 10) {
                    item.add(null);
                    dynamicRVAdapter.notifyItemInserted(items.size() - 1);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            items.remove(items.size() - 1);
                            dynamicRVAdapter.notifyItemInserted(items.size());
                            int index = items.size();
                            int end = index + 10;
                            for (int i = index; i < end; i++) {
                                String name = UUID.randomUUID().toString();
                                DynamicRVModel item = new DynamicRVModel(name);
                                items.add(item);
                            }
                            dynamicRVAdapter.notifyDataSetChanged();
                            dynamicRVAdapter.setLoaded();
                        }
                    }, 4000);

                } else {
                    Toast.makeText(HomeActivity.this, "Data Completed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Set up click listener for static RecyclerView items
        staticRvAdapter.setOnItemClickListener(new StaticRvAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(StaticRvModel item) {
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                String vehicleType = item.getText();
                DocumentReference docRef = db.collection(vehicleType).document();
                docRef.get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        android.util.Log.d("TAG", "DocumentSnapshot data: " + task.getResult().getData());
                        //username.getEditText().setText(task.getResult().getData().get("username").toString());
                    } else {
                        android.util.Log.d("TAG", "get failed with ", task.getException());
                    }
                });
                //addItemToDynamicRecyclerView(item);
            }
        });
    }

    private void addItemToDynamicRecyclerView(StaticRvModel staticRvModel) {
        // Extract information from the clicked static item
        String itemName = staticRvModel.getText();
        int itemImage = staticRvModel.getImage();

        // Create a DynamicRVModel with the extracted information
        DynamicRVModel dynamicRVModel = new DynamicRVModel(itemName);
        items.clear();
        // Add the item to the dynamic RecyclerView's adapter
        items.add(dynamicRVModel);

        // Notify the adapter that the dataset has changed
        dynamicRVAdapter.notifyDataSetChanged();
    }

}
