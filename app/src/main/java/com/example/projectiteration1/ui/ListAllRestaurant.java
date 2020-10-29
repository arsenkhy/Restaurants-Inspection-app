package com.example.projectiteration1.ui;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectiteration1.R;
import com.example.projectiteration1.adapter.RestaurantAdapter;

import java.util.ArrayList;

public class ListAllRestaurant extends AppCompatActivity {
    private RecyclerView recyclerList;
    private RestaurantAdapter resAdapter;
    private RecyclerView.LayoutManager listLayout;
    /*
        TODO
        Change to use Datatype/Class used to hold restaurant datalist
    */
    private ArrayList<Integer> resList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_restaurants);
        /*
            TODO
            Needs Singleton class for list of data
        */
        //resList

        setUpList();
    }

    /*  Set up RecyclerView
        https://developer.android.com/guide/topics/ui/layout/recyclerview
    */
    private void setUpList(){
        recyclerList = findViewById(R.id.allResList);
        recyclerList.setHasFixedSize(true);
        listLayout = new LinearLayoutManager(this);
        recyclerList.setLayoutManager(listLayout);
        resAdapter = new RestaurantAdapter();
        recyclerList.setAdapter(resAdapter);
        resAdapter.setOnResClickListener(new RestaurantAdapter.OnResClickListener() {
            @Override
            public void onResClick(int pos) {
                Log.i("Main - Res Click", "@Pos: " + pos);
                /*
                    TODO
                    Switch to Single Restaurant View
                */
            }
        });
    }
}