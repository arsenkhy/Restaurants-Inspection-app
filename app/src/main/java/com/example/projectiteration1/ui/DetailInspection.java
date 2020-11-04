package com.example.projectiteration1.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectiteration1.R;
import com.example.projectiteration1.model.InspectionReport;
import com.example.projectiteration1.model.Restaurant;
import com.example.projectiteration1.model.RestaurantsList;
import com.example.projectiteration1.model.Violation;

import java.util.ArrayList;
import java.util.List;

public class DetailInspection extends AppCompatActivity {
    RestaurantsList restaurantsList;
    int inspectionIndex;
    int resIndex;
    private InspectionReport ins;
    ArrayList<Violation> mylist=new ArrayList<Violation>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_inspection);

        //retrieve the passed data
        Intent intent = getIntent();
        resIndex=intent.getIntExtra(Intent.EXTRA_INDEX,0);
        inspectionIndex=intent.getIntExtra("Inspection index",0);
        restaurantsList=restaurantsList.getInstance();
        ins=restaurantsList.getRestaurants().get(resIndex).getInspectionReports().get(inspectionIndex);


        //set up date
        TextView date=findViewById(R.id.inspectionDate);
        date.setText(restaurantsList.getRestaurants().get(resIndex).getInspectionReports().get(inspectionIndex).getInspectionDate());

        //set up inspectionType
        TextView inspectiontype=findViewById(R.id.inspectionType);
        //String inspectionDate="Inspection Date is " +restaurantsList.getRestaurants().get(resIndex).getInspectionReports().get(inspectionIndex).getInspectionDate();
        inspectiontype.setText(""+restaurantsList.getRestaurants().get(resIndex).getInspectionReports().get(inspectionIndex).getInspectionType());

        //set up number of critical issues
        TextView c=findViewById(R.id.numberofCritical);
        String critical="Number of Critical Issues is "+ins.getNumCritical();
        c.setText(""+critical);

        //set up number of noncritical issues
        TextView nonCri=findViewById(R.id.numberofNoncritical);
        String noncritical="Number of Non-Critical Issues is "+ins.getNumNonCritical();
        nonCri.setText(""+noncritical);

       //set up hazard rating
        TextView hazardLevel=findViewById(R.id.hazardLevel);
        ImageView icon=findViewById(R.id.inspectionIcon);
        hazardLevel.setText(""+ins.getHazardRating());
        if(ins.getHazardRating().equals("Low")) {
            icon.setImageResource(R.drawable.risk_low);
            hazardLevel.setTextColor(Color.parseColor("#4CBB17"));
        }
        else if(ins.getHazardRating().equals("Moderate")){
            icon.setImageResource(R.drawable.risk_medium);
            hazardLevel.setTextColor(Color.parseColor("#FF7800"));
        }
        else{
            icon.setImageResource(R.drawable.risk_high);
            hazardLevel.setTextColor(Color.parseColor("#E60000"));
        }

        populateViolationList();
        populateListView();
        registerClickCallBack();
    }

    private void registerClickCallBack(){
        ListView list=(ListView)findViewById(R.id.violationListView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Violation clickedVio= mylist.get(position);
                String mess= "The detail of the violation you clicked is "+
                        clickedVio.getDescription();
                Toast.makeText(DetailInspection.this,mess,Toast.LENGTH_LONG).show();
            }
        });
    }

    private void populateViolationList() {
        //ArrayList<Violation> list=new ArrayList<>();

        //display all the violation under that inspection
        for(int i=0; i < ins.getViolations().size();i++){

            mylist.add(ins.getViolations().get(i));
        }
    }

    private void populateListView() {
        ArrayAdapter<Violation> adapter=new MyListAdapter();
        ListView list=(ListView)findViewById(R.id.violationListView);
        list.setAdapter(adapter);
    }

    private class MyListAdapter extends ArrayAdapter<Violation>{

        public MyListAdapter(){
            super(DetailInspection.this,R.layout.item_view,mylist);
        }
        //@NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View itemView=convertView;
            if(itemView==null){
                itemView=getLayoutInflater().inflate(R.layout.item_view,parent,false);
            }
            Violation currentVio=mylist.get(position);

            //Fill the text View
            TextView severity=(TextView)itemView.findViewById(R.id.severityText);
            severity.setText(currentVio.getSeriousness());
            TextView description=(TextView)itemView.findViewById(R.id.description);
            description.setText(currentVio.getDescription());
            ImageView icon=(ImageView) itemView.findViewById(R.id.severityIcon);
            ImageView food=(ImageView) itemView.findViewById(R.id.natureofViolation);
            food.setImageResource(R.drawable.food);

            //set up the critical icon and text color
            if(currentVio.getSeriousness().equals("Critical")) {
                icon.setImageResource(R.drawable.criticalsign);
                severity.setTextColor(Color.parseColor("#E60000"));
            }
            else {
                icon.setImageResource(R.drawable.non_critical);
                severity.setTextColor(Color.parseColor("#4CBB17"));
            }
            return itemView;
        }
    }

    public static Intent makeLaunchIntent(Context context, int index) {
        Intent intent=new Intent(context, RestaurantDetail.class);
        intent.putExtra(Intent.EXTRA_CHOOSER_TARGETS, index);
        return intent;
    }

    private void extractData(){
        Intent intent=getIntent();
        inspectionIndex=intent.getIntExtra(Intent.EXTRA_CHOOSER_TARGETS, 0);
    }
}