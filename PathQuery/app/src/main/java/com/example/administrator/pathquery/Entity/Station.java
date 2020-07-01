package com.example.administrator.pathquery.Entity;

public class Station {

    //表名
    public static final String TABLENAME="station";

    //表的各个域名
    public static final String KEY_StationName="stationName";
    public static final String KEY_StationAbbreviation="stationAbbreviation";

    //属性;
    public String stationName;
    public String stationAbbreviation;
    public Station(String stationName,String stationAbbreviation){
        this.stationName=stationName;
        this.stationAbbreviation=stationAbbreviation;
    }
}

