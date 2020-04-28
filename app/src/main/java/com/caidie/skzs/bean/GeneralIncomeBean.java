package com.caidie.skzs.bean;

public class GeneralIncomeBean {
    String time;
    String sdd;
    String fkdd;
    String zsr;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSdd() {
        return sdd;
    }

    public void setSdd(String sdd) {
        this.sdd = sdd;
    }

    public String getFkdd() {
        return fkdd;
    }

    public void setFkdd(String fkdd) {
        this.fkdd = fkdd;
    }

    public String getZsr() {
        return zsr;
    }

    public void setZsr(String zsr) {
        this.zsr = zsr;
    }

    @Override
    public String toString() {
        return "GeneralIncomeBean{" +
                "time='" + time + '\'' +
                ", sdd='" + sdd + '\'' +
                ", fkdd='" + fkdd + '\'' +
                ", zsr='" + zsr + '\'' +
                '}';
    }
}
