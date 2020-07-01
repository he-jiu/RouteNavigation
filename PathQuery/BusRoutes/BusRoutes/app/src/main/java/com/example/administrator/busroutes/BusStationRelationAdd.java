package com.example.administrator.busroutes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class BusStationRelationAdd extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_station_relation_add);

        ImageButton BackButton =(ImageButton)findViewById(R.id.Back);//返回按键

        BackButton.setOnClickListener(new View.OnClickListener() {//返回
            @Override
            public void onClick(View v) {
                //setContentView(R.layout.activity_main);
            }
        });
    }
}
