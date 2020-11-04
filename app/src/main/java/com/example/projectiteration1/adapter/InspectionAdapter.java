package com.example.projectiteration1.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.icu.util.LocaleData;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectiteration1.R;
import com.example.projectiteration1.model.InspectionReport;
import com.example.projectiteration1.model.Restaurant;
import com.example.projectiteration1.model.RestaurantsList;

import org.w3c.dom.Text;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/**
 * Adapter to fit data of the restaurant's inspections
 */
public class InspectionAdapter extends RecyclerView.Adapter<InspectionAdapter.MyViewHolder> {
    RestaurantsList res_list = RestaurantsList.getInstance();
    Context context;
    ArrayList<InspectionReport> list;
    int position;
    Restaurant res = res_list.getRestaurants().get(position);

    //constructor
    public InspectionAdapter(Context c, ArrayList<InspectionReport> inspectionList, int index)
    {
        context = c;
        list = inspectionList;
        position = index;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.inspection_list, parent, false);
        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        if(list.size()==0)
        {
            InspectionReport report = new InspectionReport();
            report.setHazardRating("Low");
            report.setInspectionDate("11111111");
            report.setNumCritical(0);
            report.setNumNonCritical(0);
        }

        //hazard
        holder.hazard.setText("Hazard Level : " + list.get(position).getHazardRating());

        //critical issue
        holder.critical.setText("Critical: " + list.get(position).getNumCritical());

        //non-critical issues
        holder.nonCritical.setText("Non-critical: " + list.get(position).getNumNonCritical());

        //date
        String dateString = list.get(position).getInspectionDate();
        int year = Integer.parseInt(dateString.substring(0,4));
        int month = Integer.parseInt(dateString.substring(4,6));
        int day = Integer.parseInt(dateString.substring(6,8));

        Log.i("Dates", "Year: " + year + " Month: " + month + " Day: " + day);
        LocalDate dateInspection = LocalDate.of(year, month, day);
        LocalDate currDate = LocalDate.now();

        long daysPast = ChronoUnit.DAYS.between(dateInspection, currDate);
        Log.i("Days Past", "Days: " + daysPast);

        String textViewDate;
        if(year == 1111){
            textViewDate = "No Inspections Yet";
        }
        else if(daysPast <= 30){
            textViewDate = daysPast + " days since inspection";
        }
        else if(daysPast <= 365){
            //Month - Day
            textViewDate = "Inspected on: " + getMonth(month) + " " + day;
        }
        else{
            //Month - Year
            textViewDate = "Inspected on: " + getMonth(month) + " " + year;
        }
        holder.date.setText(textViewDate);

        //image
        if(list.get(position).getHazardRating().equals("Low")) {
            holder.risk.setImageResource(R.drawable.risk_low);
            holder.hazard.setTextColor(Color.parseColor("#4CBB17"));
        }
        else if(list.get(position).getHazardRating().equals("Moderate")){
            holder.risk.setImageResource(R.drawable.risk_medium);
            holder.hazard.setTextColor(Color.parseColor("#FF7800"));
        }
        else{
            holder.risk.setImageResource(R.drawable.risk_high);
            holder.hazard.setTextColor(Color.parseColor("#E60000"));
        }
    }

    private String getMonth(int month){
        switch(month){
            case 1: return "Jan";
            case 2: return "Feb";
            case 3: return "Mar";
            case 4: return "Apr";
            case 5: return "May";
            case 6: return "Jun";
            case 7: return "Jul";
            case 8: return "Aug";
            case 9: return "Sept";
            case 10: return "Oct";
            case 11: return "Nov";
            case 12: return "Dec";
            default: return "No Inspection";
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /*public static Intent makeLaunchIntent(Context c, int index){
        return new Intent(c, DetailInspection.class)
    }*/

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView hazard, date, critical, nonCritical;
        ImageView risk;
        ConstraintLayout inspection_layout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            hazard = itemView.findViewById(R.id.HazardLevel);
            date = itemView.findViewById(R.id.date);
            critical = itemView.findViewById(R.id.critical);
            nonCritical = itemView.findViewById(R.id.nonCritical);
            risk = itemView.findViewById(R.id.RiskImage);
            inspection_layout = itemView.findViewById(R.id.inspection_layout);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*Intent intent = new Intent(context, DetailInspection.class);
                    intent.putExtra("index", position);
                    String chooseInspection = intent.getStringExtra("Inspection Date" + res.getInspectionReports().get(position).getInspectionDate());
                    context.startActivity(intent);*/
                }
            });
        }
    }
}