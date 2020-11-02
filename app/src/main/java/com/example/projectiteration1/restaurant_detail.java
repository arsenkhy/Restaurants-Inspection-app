/*
package com.example.projectiteration1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class restaurant_detail extends AppCompatActivity {

    private Restaurant restaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);

        //singleton class
        restaurant = Restaurant.getInstance();

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
        TextView name = (TextView) findViewById(R.id.name);
        String res_name = restaurant.getResName();
        name.setText(""+res_name);
        //set address
        TextView address = (TextView) findViewById(R.id.address);
        String res_address = restaurant.getAddress();
        address.setText(""+res_address);
        //set gps co-ords
        TextView gps = (TextView) findViewById(R.id.gps);
        String latitude= restaurant.getLatitude();
        String longitude= restaurant.getLongitude();
        gps.setText("" + latitude + "lat " + longitude + "long");
        //set image for restaurant
    }

    private void set_inspection_list() {
        //TO-DO
        //get list in recycler view
        //set up on click listener
    }
}
 */