package com.example.projectiteration1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.projectiteration1.model.RestaurantsList;

public class restaurant_detail extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RestaurantsList res_list;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);

        res_list = RestaurantsList.getInstance();

        extractData();
        restaurantDetails();
        inspectionList();
    }

    //intent
    public static Intent makeIntent(Context context, int index) {
        Intent intent=new Intent(context, restaurant_detail.class);
        intent.putExtra("Restaurant Index ", index);
        return intent;
    }

    private void extractData(){
        Intent intent=getIntent();
        index=intent.getIntExtra("Restaurant Index", 0);
    }

    @SuppressLint("SetTextI18n")
    private void restaurantDetails() {

        //set Name of restaurant
        TextView name = findViewById(R.id.name);
        String res_name = res_list.getRestaurant(index).getResName();
        name.setText("" + res_name);

        //set address of Restaurant
        TextView address = findViewById(R.id.address);
        String res_address = res_list.getRestaurant(index).getAddress();
        address.setText("" + res_address);

        //set gps
        TextView gps = findViewById(R.id.gps);
        String res_lat = res_list.getRestaurant(index).getLatitude();
        String res_long = res_list.getRestaurant(index).getLongitude();
        gps.setText(res_lat + "latitude\n" + res_long + "longitude");

        //set image of restaurant
    }

    private void inspectionList() {
        recyclerView = findViewById(R.id.inspection_list);

    }

}