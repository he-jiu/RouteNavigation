package com.example.administrator.pathquery.Entity;

public class BusNumber {
    public String busName;
    public String busStartStation;
    public String busEndStation;
    public String departTime;
    public String reachTime;

    public BusNumber(String busName,String busStartStation,String departTime,String busEndStation,String reachTime){
        this.busName=busName;
        this.busStartStation=busStartStation;
        this.departTime=departTime;
        this.busEndStation=busEndStation;
        this.reachTime=reachTime;
    }
}
