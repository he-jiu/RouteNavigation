package com.example.administrator.pathquery.DAO;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.administrator.pathquery.Entity.Bus;
import com.example.administrator.pathquery.Entity.Station;
import com.example.administrator.pathquery.Entity.Trancepos;

/**
 * 数据库创建、更新
 */
public class SQLiteHelper extends SQLiteOpenHelper {

    //数据库版本
     private static final int DATABASE_VERSION=1;

     //数据库名称
     private static final String DATABASE_NAME="PathQueryDataBase";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // 创建数据库
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_Station="CREATE TABLE "+ Station.TABLENAME+"("
                         +Station.KEY_StationName+" CHAR(20) PRIMARY KEY ,"
                         +Station.KEY_StationAbbreviation+" CHAR(20))";
        db.execSQL(CREATE_TABLE_Station);

        String CREATE_TABLE_Bus="CREATE TABLE "+ Bus.TABLENAME+"("
                +Bus.KEY_BusName+" CHAR(20) PRIMARY KEY ,"
                +Bus.KEY_BusType+" CHAR(20),"
                +Bus.KEY_BusStartStation+" CHAR(20),"
                +Bus.KEY_BusEndStation+" CHAR(20))";
        db.execSQL(CREATE_TABLE_Bus);

        String CREATE_TABLE_Trancepos="CREATE TABLE "+ Trancepos.TABLENAME+"("
                +Trancepos.KEY_BusName+" CHAR(20) ,"
                +Trancepos.KEY_StationName+" CHAR(20),"

                +Trancepos.KEY_ReachStationTime+" CHAR(20),"
                +Trancepos.KEY_QuitStationTime+" CHAR(20),"
                +"PRIMARY KEY(busName,stationName))";
        db.execSQL(CREATE_TABLE_Trancepos);
    }

    // 数据库更新
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //如果旧表存在，删除，所以数据将会消失
        //db.execSQL("DROP TABLE IF EXISTS "+ Station.TABLENAME);
        //db.execSQL("DROP TABLE IF EXISTS "+ Bus.TABLENAME);
        //db.execSQL("DROP TABLE IF EXISTS "+ Trancepos.TABLENAME);

        //再次创建表
        //onCreate(db);
    }
}
