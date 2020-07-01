package com.example.administrator.pathquery.DAO;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.pathquery.Entity.Bus;

public class BusRepo {
    private SQLiteHelper sqliteHelper;

    public BusRepo(Context context){
        sqliteHelper=new SQLiteHelper(context);
    }

    public boolean insert(Bus bus){
        //打开连接，写入数据
        ContentValues values=new ContentValues();
        values.put(Bus.KEY_BusName,bus.busName);
        values.put(Bus.KEY_BusType,bus.busType);
        values.put(Bus.KEY_BusStartStation,bus.busStartStation);
        values.put(Bus.KEY_BusEndStation,bus.busEndStation);
        if(isExist(bus)){
            return false;
        }
        else {
            SQLiteDatabase db=sqliteHelper.getWritableDatabase();
            db.insert(Bus.TABLENAME, null, values);
            db.close();
            return true;
        }
    }

    public void delete(){
        SQLiteDatabase db=sqliteHelper.getWritableDatabase();
        db.execSQL("DELETE FROM "+ Bus.TABLENAME);
        db.close();
    }

    /*public BusNumber BusNumberQuery(String busName){
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

        String timeQuery2="SELECT "+Trancepos.KEY_ReachStationTime
                +" FROM "+Trancepos.TABLENAME
                +" WHERE " +Trancepos.KEY_StationName+" = ?;";
        Cursor cur2 = db.rawQuery(timeQuery2, new String[] {busEndStation});
        String reachTime=cur2.getString(cur2.getColumnIndex(Trancepos.KEY_ReachStationTime));
        return new BusNumber(busName,busStartStation,departTime,busEndStation,reachTime);
    }*/

    public boolean isExist(Bus bus){
        SQLiteDatabase db=sqliteHelper.getWritableDatabase();
        String busQuery="SELECT *"
                +" FROM "+Bus.TABLENAME+";";
        Cursor cursor = db.rawQuery(busQuery, null);
        while (cursor.moveToNext()) {
            String busName = cursor.getString(cursor.getColumnIndex(Bus.KEY_BusName));
            if (busName .equals(bus.busName)) {
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
