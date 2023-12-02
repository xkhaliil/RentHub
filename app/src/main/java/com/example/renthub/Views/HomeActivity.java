package com.example.renthub.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.renthub.R;
import com.example.renthub.Views.DRVinterface.LoadMore;

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

       /*   items.add(new DynamicRVModel("SUV"));
        items.add(new DynamicRVModel("CONVERTIBLE"));
        items.add(new DynamicRVModel("COUPE"));
        items.add(new DynamicRVModel("HATCHBACK"));
        items.add(new DynamicRVModel("SEDAN"));
        items.add(new DynamicRVModel("Sports"));
        items.add(new DynamicRVModel("TRUCK"));
        items.add(new DynamicRVModel("VAN"));
        items.add(new DynamicRVModel("PICKUP"));
        items.add(new DynamicRVModel("MINI_BUS"));*/

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
                // Handle item click by adding it to the dynamic RecyclerView
                addItemToDynamicRecyclerView(item);
            }
        });
    }

    private void addItemToDynamicRecyclerView(StaticRvModel staticRvModel) {
        // Extract information from the clicked static item
        String itemName = staticRvModel.getText();
        int itemImage = staticRvModel.getImage();

        // Create a DynamicRVModel with the extracted information
        DynamicRVModel dynamicRVModel = new DynamicRVModel(itemName);

        // Add the item to the dynamic RecyclerView's adapter
        items.add(dynamicRVModel);

        // Notify the adapter that the dataset has changed
        dynamicRVAdapter.notifyDataSetChanged();
    }
}
