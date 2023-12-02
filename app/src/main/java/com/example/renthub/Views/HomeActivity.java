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
        item.add(new StaticRvModel(R.drawable.car, "Popular"));
        item.add(new StaticRvModel(R.drawable.car, "SUV"));
        item.add(new StaticRvModel(R.drawable.car, "Limo"));
        item.add(new StaticRvModel(R.drawable.car, "Truck"));
        item.add(new StaticRvModel(R.drawable.car, "Luxury"));
        item.add(new StaticRvModel(R.drawable.car, "Sports"));


        recyclerView= findViewById(R.id.rv_1);
        staticRvAdapter = new StaticRvAdapter(item);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(staticRvAdapter);

        items.add(new DynamicRVModel("Truck"));
        items.add(new DynamicRVModel("SUV"));
        items.add(new DynamicRVModel("Sports"));
        items.add(new DynamicRVModel("Limo"));
        items.add(new DynamicRVModel("Luxury"));
        items.add(new DynamicRVModel("Popular"));

        RecyclerView drv = findViewById(R.id.rv_2);
        drv.setLayoutManager(new LinearLayoutManager(this));
        dynamicRVAdapter = new DynamicRVAdapter(drv, this,items);
        drv.setAdapter(dynamicRVAdapter);

        dynamicRVAdapter.setLoadMore(new LoadMore() {
            @Override
            public void onLoadMore() {
                if(items.size()<=10){
                    item.add(null);
                    dynamicRVAdapter.notifyItemInserted(items.size()-1);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            items.remove(items.size()-1);
                            dynamicRVAdapter.notifyItemInserted(items.size());
                            int index  = items.size();
                            int end = index+10;
                            for (int i = index; i<end;i++){
                                String name = UUID.randomUUID().toString();
                                DynamicRVModel item = new DynamicRVModel(name);
                                items.add(item);
                            }
                            dynamicRVAdapter.notifyDataSetChanged();
                            dynamicRVAdapter.setLoaded();
                        }
                    },4000);

                }
                else
                    Toast.makeText(HomeActivity.this, "Data Completed", Toast.LENGTH_SHORT).show();
            }
        });





    }
}