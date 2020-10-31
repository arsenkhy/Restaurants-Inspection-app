package com.example.projectiteration1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.projectiteration1.model.Restaurant;
import com.example.projectiteration1.model.RestaurantsList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class MainActivity extends AppCompatActivity {

    private RestaurantsList restaurantList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get singleton class of restaurants
        restaurantList = RestaurantsList.getInstance();

        // Read restaurant data from csv.
        readRestaurantData();
    }

    // Used course tutorial at: https://www.youtube.com/watch?v=i-TqNzUryn8
    private void readRestaurantData() {
        InputStream stream = getResources().openRawResource(R.raw.restaurants_itr1);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(stream, Charset.forName("UTF-8"))
        );

        String eachLine = "";
        final String NO_DATA = "No data";
        try {
            // Step over a header
            reader.readLine();
            while ( (eachLine = reader.readLine()) != null) {

                // Split ','
                String[] information = eachLine.split(",");

                // Set default value for empty strings
                int index = 0;
                for (String s : information) {
                    if (s.length() == 0) {
                        information[index] = NO_DATA;
                    }
                    index++;
                }

                // Read the data
                Restaurant restaurant = new Restaurant();
                restaurant.setTrackingNumber(information[0]);
                restaurant.setResName(information[1]);
                restaurant.setAdress(information[2]);
                restaurant.setCity(information[3]);
                restaurant.setFacType(information[4]);
                restaurant.setLatitude(information[5]);

                // Check if last attribute is not empty
                if (information.length >= 7 && information[6].length() > 0) {
                    restaurant.setLongitude(information[6]);
                } else {
                    restaurant.setLongitude(NO_DATA);
                }

                // Add the data
                restaurantList.add(restaurant);

                // Display the restaurants for debugging
                Log.d("MyActivty", "Just created: " + restaurant);
            }
        } catch (IOException e) {
            // Error reading internal file
            Log.wtf("MainActivity", "Error reading the file" + eachLine, e);
            e.printStackTrace();
        }
    }

}