package com.example.projectiteration1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectiteration1.R;

import java.util.ArrayList;

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
        int imageID = 0;
        holder.resImage.setImageResource(imageID);
        String name = "";
        holder.resName.setText(name);
        String issues = "";
        holder.resIssueFound.setText(issues);
        String date = "";
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
        public TextView resName;
        public TextView resIssueFound;
        public TextView resIssueDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            resImage = itemView.findViewById(R.id.restaurantIcon);
            resName = itemView.findViewById(R.id.restaurantName);
            resIssueFound = itemView.findViewById(R.id.restaurantIssueFound);
            resIssueDate = itemView.findViewById(R.id.restaurantIssueDate);

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
