package com.example.projectiteration1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.projectiteration1.model.InspectionReport;
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

    private RestaurantsList restaurantList;                                 // List of restaurants
    private ArrayList<InspectionReport> reportsList = new ArrayList<>();    // List of reports. Read from csv

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Read reports data from csv.
        readReportsData();

        // Get singleton class of restaurants
        restaurantList = RestaurantsList.getInstance();

        // Read restaurant data from csv.
        readRestaurantData();

        // Assign reports to a restaurant
        assignInspectionReportsToRes();

        // Sort the restaurants in alphabetical order
        restaurantList.sortByName();
    }

    private void assignInspectionReportsToRes() {
        // Inspection Reports for a single restaurant
        ArrayList<InspectionReport> oneRestaurantReports = new ArrayList<>();

        for (Restaurant restaurant : restaurantList.getRestaurants()) {
            for (InspectionReport report : reportsList) {
                // Check corresponding tracking numbers
                if (restaurant.getTrackingNumber().equals(report.getTrackingNumber())) {
                    oneRestaurantReports.add(report);
                }
            }
            // Set the reports to a restaurant
            restaurant.setInspectionReports(oneRestaurantReports);
            oneRestaurantReports = new ArrayList<>();               // Clean up space for new iteration

            // For Debugging purposes
            Log.d("MainActivity", "Assigned Reports to "
                    + restaurant.getResName() + ": "
                    + restaurant.getInspectionReports());
        }

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

                int index = 0;
                for (String s : information) {
                    // Set default data
                    if (s.length() == 0) {
                        information[index] = NO_DATA;
                    } else { // Remove unnecessary character '"' from string
                        information[index] = removeQuotationMark(information[index]);
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
                Log.d("MyActivty", "Just created: " + restaurant);          // Will not show list of reports yet, Assigned later in program
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

                int index = 0;
                for (String s : information) {
                    // The string index where line of violations starts
                    if (index == 6) {
                        break;
                    }
                    // Set default value for empty strings
                    if (s.length() == 0) {
                        information[index] = NO_DATA;
                    } else {
                        // Remove unnecessary character '"' from string
                        information[index] = removeQuotationMark(information[index]);
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
                Log.d("MyActivity", "Just created: " + report);
            }
        } catch (IOException e) {
            // Error reading internal file
            Log.wtf("MainActivity", "Error reading the file" + eachLine, e);
            e.printStackTrace();
        }
    }

    // When reading strings it leaves "" marks on a string attributes
    private String removeQuotationMark(String beforeString) {
        char quotation = 34;                        // Stands for '"'
        String s = String.valueOf(quotation);

        // Delete all '"'
        String afterString = beforeString.replace(s,"");
        return afterString;
    }

    private ArrayList<Violation> getViolations (String[] violationsLine, int startIndex) {
        ArrayList<Violation> toReturn = new ArrayList<>();
        // Line of all violations
        StringBuffer allViolations = new StringBuffer();
        for (int i = startIndex; i < violationsLine.length; i++) {
            allViolations.append(violationsLine[i]).append(",");
        }
        String lineOfViolations = allViolations.toString();         // Get String

        // The strings of all violations in the line
        ArrayList<String> violationList = new ArrayList<>();

        // The indexes for reading the list of all violations in a line
        int start = 0;
        int end = 0;
        // Until read all line
        while (end < lineOfViolations.length()) {
            // New violation started
            if (lineOfViolations.charAt(end) == '|') {
                violationList.add(lineOfViolations.substring(start + 1, end));
                start = end;
            } else if (end == lineOfViolations.length() - 2) {                  // Last violation in the line
                violationList.add(lineOfViolations.substring(start + 1, end));
            }
            end++;
        }

        // Set all violations into one report
        for (String singleViolation : violationList) {
            String[] attributes = singleViolation.split(",");       // Attributes of one violation
            Violation violation = new Violation(
                    Integer.parseInt(attributes[0]),                      // Violation ID
                    attributes[1],                                        // Seriousness
                    attributes[2],                                        // Description
                    attributes[3]);                                       // Reappearance
            toReturn.add(violation);
        }

        return toReturn;
    }

}