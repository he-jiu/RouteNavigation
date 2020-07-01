package com.example.administrator.busroutes;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.time.Instant;

public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton BusAddButton = (ImageButton) findViewById(R.id.BusAdd);
        ImageButton StationAddButton = (ImageButton) findViewById(R.id.StationAdd);
        ImageButton BusStationRelationAddButton = (ImageButton) findViewById(R.id.BusStationRelationAdd);
        ImageButton BackButton = (ImageButton)findViewById(R.id.Back);

        BackButton.setOnClickListener(new View.OnClickListener() {//返回主界面
            @Override
            public void onClick(View v) {
                //setContentView(R.layout);
            }
        });

        BusAddButton.setOnClickListener(new View.OnClickListener() {//添加车次
            @Override

            public void onClick(View v) {
                //setContentView(R.layout.activity_bus_add);
                Intent intent = new Intent(MainActivity.this,BusAdd.class);
                startActivity(intent);
            }
        });

        StationAddButton.setOnClickListener(new View.OnClickListener() {//添加车站
            @Override
            public void onClick(View v) {
                //setContentView(R.layout.activity_station_add);
                Intent intent = new Intent(MainActivity.this,StationAdd.class);
                startActivity(intent);
            }
        });

        BusStationRelationAddButton.setOnClickListener(new View.OnClickListener() {//添加车次车站关系
            @Override
            public void onClick(View v) {
                //setContentView(R.layout.activity_bus_station_relation_add);
                Intent intent = new Intent(MainActivity.this,BusStationRelationAdd.class);
                startActivity(intent);
            }
        });

    }
}
