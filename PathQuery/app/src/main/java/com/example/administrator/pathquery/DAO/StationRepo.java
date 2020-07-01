package com.example.administrator.pathquery.DAO;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.administrator.pathquery.Entity.Station;


public class StationRepo {
    private SQLiteHelper sqliteHelper;

    public StationRepo(Context context){
        sqliteHelper=new SQLiteHelper(context);
    }

    public boolean insert(Station station){
        //打开连接，写入数据

        ContentValues values=new ContentValues();
        values.put(Station.KEY_StationName,station.stationName);
        values.put(Station.KEY_StationAbbreviation,station.stationAbbreviation);

        if(!isExist(station)){
            SQLiteDatabase db=sqliteHelper.getWritableDatabase();
            db.insert(Station.TABLENAME, null, values);
            db.close();
            return true;
        }
        else {
            return false;
        }
    }

    public void delete(){
        SQLiteDatabase db=sqliteHelper.getWritableDatabase();
        db.execSQL("DELETE FROM "+ Station.TABLENAME);
        db.close();
    }

    public boolean isExist(Station station){
        SQLiteDatabase db=sqliteHelper.getWritableDatabase();
        String busQuery="SELECT *"
                +" FROM "+Station.TABLENAME+";";
        Cursor cursor = db.rawQuery(busQuery, null);
        while (cursor.moveToNext()) {
            Log.e("111","111111111");
            String stationName = cursor.getString(cursor.getColumnIndex(Station.KEY_StationName));
            if (stationName.equals( station.stationName)) {
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

