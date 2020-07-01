package com.example.administrator.pathquery.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.administrator.pathquery.Entity.Bus;
import com.example.administrator.pathquery.Entity.BusNumber;
import com.example.administrator.pathquery.Entity.Station;
import com.example.administrator.pathquery.Entity.Trancepos;
import com.example.administrator.pathquery.GJCXActivity;

import java.util.ArrayList;

public class PathQuery {
    private SQLiteHelper sqliteHelper;

    public PathQuery(Context context){
        sqliteHelper=new SQLiteHelper(context);
    }

    public ArrayList<Station> AllStation(){
        ArrayList<Station> stations=new ArrayList<Station>();
        SQLiteDatabase db=sqliteHelper.getWritableDatabase();
        String stationQuery="SELECT *"
                +" FROM "+Station.TABLENAME+";";
        Cursor cursor = db.rawQuery(stationQuery, null);
        while (cursor.moveToNext()) {
            String stationNzme=cursor.getString(cursor.getColumnIndex(Station.KEY_StationName));
            String stationAbbreviation=cursor.getString(cursor.getColumnIndex(Station.KEY_StationAbbreviation));
            Station sta = new Station(stationNzme,stationAbbreviation);
            stations.add(sta);
        }
        cursor.close();
        db.close();
        return stations;
    }

    public BusNumber BusNumberQuery(String busName){
        SQLiteDatabase db=sqliteHelper.getWritableDatabase();
        String busQuery="SELECT *"
                +" FROM "+Bus.TABLENAME
                +" WHERE " +Bus.KEY_BusName+" = ?;";
        Cursor cursor = db.rawQuery(busQuery, new String[] {busName});
        String busStartStation=cursor.getString(cursor.getColumnIndex(Bus.KEY_BusStartStation));
        String busEndStation=cursor.getString(cursor.getColumnIndex(Bus.KEY_BusEndStation));
        cursor.close();

        String timeQuery="SELECT "+Trancepos.KEY_QuitStationTime
                +" FROM "+Trancepos.TABLENAME
                +" WHERE " +Trancepos.KEY_StationName+" = ?;";
        Cursor cur = db.rawQuery(timeQuery, new String[] {busStartStation});
        String departTime=cur.getString(cur.getColumnIndex(Trancepos.KEY_QuitStationTime));
        cur.close();

        String timeQuery2="SELECT "+Trancepos.KEY_ReachStationTime
                +" FROM "+Trancepos.TABLENAME
                +" WHERE " +Trancepos.KEY_StationName+" = ?;";
        Cursor cur2 = db.rawQuery(timeQuery2, new String[] {busEndStation});
        cur2.close();
        String reachTime=cur2.getString(cur2.getColumnIndex(Trancepos.KEY_ReachStationTime));
        db.close();
        return new BusNumber(busName,busStartStation,departTime,busEndStation,reachTime);
    }

    public ArrayList<Bus> StationQuery(String stationName){
        ArrayList<Bus> buses=new ArrayList<Bus>();
        SQLiteDatabase db=sqliteHelper.getWritableDatabase();
        String stationQuery="SELECT "+Trancepos.KEY_BusName
                +" FROM "+Trancepos.TABLENAME
                +" WHERE " +Trancepos.KEY_StationName+" = ?;";
        Cursor cursor = db.rawQuery(stationQuery, new String[] {stationName});
        while (cursor.moveToNext()) {
            String busName=cursor.getString(cursor.getColumnIndex(Trancepos.KEY_BusName));
            String busQuery="SELECT *"
                    +" FROM "+Bus.TABLENAME
                    +" WHERE " +Bus.KEY_BusName+" = ?;";
            Cursor cur = db.rawQuery(busQuery, new String[] {busName});
            while (cur.moveToNext()){
                String busType=cur.getString(cur.getColumnIndex(Bus.KEY_BusType));
                String busStartStation=cur.getString(cur.getColumnIndex(Bus.KEY_BusStartStation));
                String busEndStation=cur.getString(cur.getColumnIndex(Bus.KEY_BusEndStation));
                Bus bus=new Bus(busName,busType,busStartStation,busEndStation);
                buses.add(bus);
            }
            cur.close();
        }
        cursor.close();
        db.close();
        return buses;
    }

    public ArrayList<BusNumber> SSQuery(String station1,String station2){

        ArrayList<BusNumber> busBumber = new ArrayList<BusNumber>();
        ArrayList<Bus> buses = new ArrayList<Bus>();
        ArrayList<Bus> bus1=StationQuery(station1);
        ArrayList<Bus> bus2=StationQuery(station2);
        for(int i=0;i<bus1.size();i++){
            for(int j=0;j<bus1.size();j++){
                if(bus1.get(i).busName.equals(bus2.get(j).busName)){
                    buses.add(bus1.get(i));
                }
            }
        //    if(bus2.contains(bus1.get(i))){
          //      buses.add(bus1.get(i));
           // }
        }
        Log.e("123",String.valueOf(bus1.size()));
        for(int j=0;j<buses.size();j++) {
            String timeQuery = "SELECT " + Trancepos.KEY_QuitStationTime
                    + " FROM " + Trancepos.TABLENAME
                    + " WHERE " + Trancepos.KEY_StationName + " = ? and "
                    +Trancepos.KEY_BusName+" = ?;";
            SQLiteDatabase db=sqliteHelper.getWritableDatabase();
            Cursor cur1 = db.rawQuery(timeQuery, new String[]{station1,buses.get(j).busName});

            cur1.close();

            String timeQuery2 = "SELECT " + Trancepos.KEY_ReachStationTime
                    + " FROM " + Trancepos.TABLENAME
                    + " WHERE " + Trancepos.KEY_StationName + " = ? and "
                    +Trancepos.KEY_BusName+" = ?;";
            Cursor cur2 = db.rawQuery(timeQuery2, new String[]{station2,buses.get(j).busName});

            cur2.close();
            busBumber.add(new BusNumber(buses.get(j).busName,station1,"welcome",station2,"you"));
        }
        return busBumber;
    }
    public ArrayList<Station> BusNumberQuery2(String busName){
        ArrayList<Station> stations = new ArrayList<Station>();
        SQLiteDatabase db=sqliteHelper.getWritableDatabase();
        String stationQuery="SELECT *"
                +" FROM "+Trancepos.TABLENAME
                +" WHERE " +Trancepos.KEY_BusName+" = ?;";
        Cursor cursor = db.rawQuery(stationQuery, new String[] {busName});
        while (cursor.moveToNext()) {
            String stationName=cursor.getString(cursor.getColumnIndex(Trancepos.KEY_StationName));
            String bQuery="SELECT *"
                    +" FROM "+Station.TABLENAME
                    +" WHERE " +Station.KEY_StationName+" = ?;";
            Cursor cur = db.rawQuery(bQuery, new String[] {stationName});
            while (cur.moveToNext()) {
                String stationAbbreviation = cur.getString(cur.getColumnIndex(Station.KEY_StationAbbreviation));
                Station s = new Station(stationName,stationAbbreviation);
                stations.add(s);
            }
            cur.close();
        }
        cursor.close();
        db.close();
        return stations;
    }
}
