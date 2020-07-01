package com.example.administrator.pathquery.Entity;

public class Trancepos {
    //表名
    public static final String TABLENAME="trancepos";

    //表的各个域名
    public static final String KEY_BusName="busName";
    public static final String KEY_StationName="stationName";
    public static final String KEY_ReachStationTime="reachStationTime";
    public static final String KEY_QuitStationTime="quitStationTime";

    //属性;
    public String busName;
    public String stationName;
    public String reachStationTime;
    public String quitStationTime;

    public Trancepos(String busName,String stationName,String reachStationTime,String quitStationTime){
        this.busName=busName;
        this.stationName=stationName;
        this.reachStationTime=reachStationTime;
        this.quitStationTime=quitStationTime;
    }
}
