package com.example.administrator.pathquery.Entity;

public class Bus {
    //表名
    public static final String TABLENAME="bus";

    //表的各个域名
    public static final String KEY_BusName="busName";
    public static final String KEY_BusType="busType";
    public static final String KEY_BusStartStation="busStartStation";
    public static final String KEY_BusEndStation="busEndStation";

    //属性;
    public String busName;
    public String busType;
    public String busStartStation;
    public String busEndStation;

    public Bus(String busName,String busType,String busStartStation,String busEndStation){
        this.busName=busName;
        this.busType=busType;
        this.busStartStation=busStartStation;
        this.busEndStation=busEndStation;
    }
}
