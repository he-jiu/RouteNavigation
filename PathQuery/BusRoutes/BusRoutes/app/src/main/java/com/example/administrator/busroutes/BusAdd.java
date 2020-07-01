package com.example.administrator.busroutes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class BusAdd extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_add);

        ImageButton BackButton =(ImageButton)findViewById(R.id.Back);
        final EditText BusMessage=(EditText)findViewById(R.id.BusMessage);
        final EditText BusType=(EditText)findViewById(R.id.BusType);
        final EditText StartStation=(EditText)findViewById(R.id.StartStation);
        final EditText EndStation=(EditText)findViewById(R.id.EndStation);

        Button submitButton = (Button)findViewById(R.id.submit);

        BackButton.setOnClickListener(new View.OnClickListener() {//跳回主界面
            @Override
            public void onClick(View v) {
                //setContentView(R.layout.activity_main);

            }

        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String TheBusMessage=BusMessage.getText().toString();
//                String TheBusType=BusType.getText().toString();
//                String TheStartStation=StartStation.getText().toString();
//                String TheEndStation=EndStation.getText().toString();
//                System.out.println(TheBusMessage+" "+TheBusType+" "+TheStartStation+" "+TheEndStation);

            }
        });
    }
}
