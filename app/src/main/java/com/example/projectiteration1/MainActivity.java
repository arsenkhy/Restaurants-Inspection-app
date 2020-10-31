package com.example.projectiteration1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.projectiteration1.model.InspectionReport;
import com.example.projectiteration1.model.ReportsList;
import com.example.projectiteration1.model.Restaurant;
import com.example.projectiteration1.model.RestaurantsList;
import com.example.projectiteration1.model.Violation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RestaurantsList restaurantList;
    private ReportsList reportsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get singleton class of restaurants
        restaurantList = RestaurantsList.getInstance();

        // Read restaurant data from csv.
        readRestaurantData();

        // Sort the restaurants in alphabetical order
        restaurantList.sortByName();

        // Get singleton class of reports
        reportsList = ReportsList.getInstance();

        // Read reports data from csv.
        readReportsData();
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
                restaurant.setAddress(information[2]);
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

    // Used course tutorial at: https://www.youtube.com/watch?v=i-TqNzUryn8
    private void readReportsData() {
        InputStream stream = getResources().openRawResource(R.raw.inspectionreports_itr1);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(stream, Charset.forName("UTF-8"))
        );

        final String NO_DATA = "No data available";
        String eachLine = "";
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
                InspectionReport report = new InspectionReport();
                report.setTrackingNumber(information[0]);
                report.setInspectionDate(information[1]);
                report.setInspectionType(information[2]);
                report.setNumCritical(Integer.parseInt(information[3]));
                report.setNumNonCritical(Integer.parseInt(information[4]));
                report.setHazardRating(information[5]);

                // Check if last attribute is not empty
                if (information.length >= 7 && information[6].length() > 0) {
                    report.setViolations(getViolations(information, 6));
                } else {
                    // Default set for empty ArrayList for strings
                    report.setViolations(new ArrayList<Violation>());
                }

                // Add the data
                reportsList.add(report);

                // Display the restaurants for debugging
                Log.d("MyActivty", "Just created: " + report);
            }
        } catch (IOException e) {
            // Error reading internal file
            Log.wtf("MainActivity", "Error reading the file" + eachLine, e);
            e.printStackTrace();
        }
    }

    private ArrayList<Violation> getViolations (String[] violationsLine, int startIndex) {
        ArrayList<Violation> toReturn = new ArrayList<>();
        StringBuffer allViolations = new StringBuffer();
        for (int i = startIndex; i < violationsLine.length; i++) {
            allViolations.append(violationsLine[i]).append(",");
        }
        String str = allViolations.toString();
        System.out.println(str);


        return toReturn;
    }

}