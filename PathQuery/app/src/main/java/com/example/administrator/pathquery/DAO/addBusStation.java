package com.example.administrator.pathquery.DAO;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.administrator.pathquery.Entity.Trancepos;
import com.example.administrator.pathquery.R;

public class addBusStation extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bus_station);


        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        final EditText StationName = (EditText)findViewById(R.id.StationName);
        final EditText ArriveTime = (EditText)findViewById(R.id.arriveTime);
        final EditText LeaveTime = (EditText)findViewById(R.id.leaveTime);
        final EditText ForwardNumber = (EditText)findViewById(R.id.ForwardNumber);
        final EditText BackwardNumber = (EditText)findViewById(R.id.BackwardNumber);

        Button submit = (Button)findViewById(R.id.submit);
        ImageButton backButton = (ImageButton)findViewById(R.id.backButton);

        Intent intent=getIntent();
        final String busName = intent.getStringExtra("busName");

        submit.setOnClickListener(new View.OnClickListener() {//提交数据库
            @Override
            public void onClick(View v) {
                String StationNameString = StationName.getText().toString();
                String ArriveTimeString = ArriveTime.getText().toString();
                String LeaveTimeString = LeaveTime.getText().toString();
                String ForwardNumberString = ForwardNumber.getText().toString();
                String BackwardNumberString = BackwardNumber.getText().toString();

                Trancepos trancepos=new Trancepos(busName,StationNameString,ArriveTimeString,LeaveTimeString);
                TranceposRepo TranceposRepo = new TranceposRepo(addBusStation.this);
                if(TranceposRepo.insert(trancepos)){
                    alertDialog.setMessage("添加成功");
                }

                else{
                    alertDialog.setMessage("车站重复");
                }

                alertDialog.show();

            }

                //System.out.println("StationName:"+StationNameString +" busName:" + busName);

        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 跳回BusStationRelationAdd

            }
        });


    }
}
