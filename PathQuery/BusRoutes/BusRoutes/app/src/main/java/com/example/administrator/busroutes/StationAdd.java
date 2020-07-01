package com.example.administrator.busroutes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class StationAdd extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_add);

        ImageButton BackButton =(ImageButton)findViewById(R.id.Back);

        BackButton.setOnClickListener(new View.OnClickListener() {//跳回主界面
            @Override
            public void onClick(View v) {
                //setContentView(R.layout.activity_main);
            }
        });

    }
}
