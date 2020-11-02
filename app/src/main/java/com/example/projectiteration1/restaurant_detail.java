package com.example.projectiteration1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class restaurant_detail extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);

        rest_details();
        set_inspection_list();
    }

    //intent
    public static Intent makeIntent(Context context) {
        return new Intent(context, restaurant_detail.class);
    }

    private void rest_details(){
        //TO-DO
        //set name of restaurant
        //set address
        //set gps co-ords
    }

    private void set_inspection_list() {
        //TO-DO
        //get list in recycler view
    }
}