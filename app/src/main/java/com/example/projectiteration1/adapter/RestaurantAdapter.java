package com.example.projectiteration1.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectiteration1.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder>{
    /*
        TODO
        Change to use Datatype/Class used to hold restaurant datalist
     */
    private ArrayList<Integer> resList;
    private OnResClickListener myListener;
    /*
        TODO
        Needs Singleton class for list of data
     */
    public RestaurantAdapter(){
        /*
            TODO
            Needs to grab Singleton class of List to import Data
         */
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_all, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //resList.get(position);

        //Image
        int imageID = 0;
        holder.resImage.setImageResource(imageID);

        //Name
        String name = "";
        holder.resName.setText(name);

        /*
            TODO
            Get Data from Restaurant
         */
        int critIssue = 0;
        int nonCritIssue = 0;

        String issues = "Critical: " + critIssue + " Non-Critical: " + nonCritIssue;
        String date = "";
        /*
            TODO
            Get Inspection date from Restaurant
         */
        long inspectionDate = Calendar.getInstance().getTimeInMillis();
        long currDate = Calendar.getInstance().getTimeInMillis();
        // Days = Milliseconds / (Hours in Days * Minutes in Hour * Seconds in Minute * Seconds in Milliseconds)
        float daysPast = (float)(currDate - inspectionDate) / (24 * 60 * 60 * 1000);
        if(daysPast <= 30){
            date = daysPast + " days since inspection";
        }
        else if(daysPast <= 365){
            //Month - Day
            date = inspectionDate + "" + inspectionDate;
        }
        else{
            //Month - Year
            date = inspectionDate + "" + inspectionDate;
        }

        /*
            TODO
            Get Hazard Rating from Restaurant Data
         */
        int hazardRating = 0;
        switch(hazardRating){
            case 0: // Low
                holder.resHazIcon.setImageResource(R.drawable.ic_checkmark);
                holder.resIssueFound.setTextColor(Color.GREEN);
                break;
            case 1: // Medium
                holder.resHazIcon.setImageResource(R.drawable.ic_warning);
                holder.resIssueFound.setTextColor(Color.YELLOW);
                break;
            default: // High
                holder.resHazIcon.setImageResource(R.drawable.ic_biohazard);
                holder.resIssueFound.setTextColor(Color.RED);
                break;
        }

        //Issues
        holder.resIssueFound.setText(issues);

        //Date
        holder.resIssueDate.setText(date);
    }

    @Override
    public int getItemCount() {
        /*
            TODO
            Requires Singleton class holding the Restaurant Data
         */
        return resList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView resImage;
        public ImageView resHazIcon;
        public TextView resName;
        public TextView resIssueFound;
        public TextView resIssueDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            resImage = itemView.findViewById(R.id.restaurantIcon);
            resName = itemView.findViewById(R.id.restaurantName);
            resIssueFound = itemView.findViewById(R.id.restaurantIssueFound);
            resIssueDate = itemView.findViewById(R.id.restaurantIssueDate);
            resHazIcon = itemView.findViewById(R.id.restaurantHazardIcon);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (myListener != null) {
                        int pos = getAdapterPosition();
                        if(pos >= 0 && pos < resList.size()){
                            myListener.onResClick(pos);
                        }
                    }
                }
            });
        }
    }

    public interface OnResClickListener{
        void onResClick(int pos);
    }

    public void setOnResClickListener(OnResClickListener listener){
        myListener = listener;
    }
}
