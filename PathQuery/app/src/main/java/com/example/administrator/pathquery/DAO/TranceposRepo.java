package com.example.administrator.pathquery.DAO;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.pathquery.Entity.Trancepos;

public class TranceposRepo {
    private SQLiteHelper sqliteHelper;

    public TranceposRepo(Context context){
        sqliteHelper=new SQLiteHelper(context);
    }

    public boolean insert(Trancepos trancepos){
        //打开连接，写入数据

        ContentValues values=new ContentValues();
        values.put(Trancepos.KEY_BusName,trancepos.busName);
        values.put(Trancepos.KEY_StationName,trancepos.stationName);
        values.put(Trancepos.KEY_ReachStationTime,trancepos.reachStationTime);
        values.put(Trancepos.KEY_QuitStationTime,trancepos.quitStationTime);

        if(isExist(trancepos)){
            return false;
        }
        else {
            SQLiteDatabase db=sqliteHelper.getWritableDatabase();
            db.insert(Trancepos.TABLENAME, null, values);
            db.close();
            return true;
        }
    }

    public void delete(){
        SQLiteDatabase db=sqliteHelper.getWritableDatabase();
        db.execSQL("DELETE FROM "+ Trancepos.TABLENAME);
        db.close();
    }

    /*public ArrayList<Bus> StationQuery(String stationName){
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
    }*/

    public boolean isExist(Trancepos trancepos){
        SQLiteDatabase db=sqliteHelper.getWritableDatabase();
        String busQuery="SELECT *"
                +" FROM "+Trancepos.TABLENAME+";";
        Cursor cursor = db.rawQuery(busQuery, null);
        while (cursor.moveToNext()) {
            String busName = cursor.getString(cursor.getColumnIndex(Trancepos.KEY_BusName));
            String stationName = cursor.getString(cursor.getColumnIndex(Trancepos.KEY_StationName));
            if (stationName.equals(trancepos.stationName) && busName.equals(trancepos.busName)) {
                cursor.close();
                db.close();
                return true;
            }
        }
        cursor.close();
        db.close();
        return false;
    }
}
