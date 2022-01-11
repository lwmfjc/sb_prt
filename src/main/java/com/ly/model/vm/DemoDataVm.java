package com.ly.model.vm;

import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class DemoDataVm {
    private String strData;
    private Date dateData;
    private Double doubleData;

    public String getStrData() {
        return strData;
    }

    public void setStrData(String strData) {
        this.strData = strData;
    }

    public Date getDateData() {
        return dateData;
    }

    public void setDateData(Date dateData) {
        this.dateData = dateData;
    }

    public Double getDoubleData() {
        return doubleData;
    }

    public void setDoubleData(Double doubleData) {
        this.doubleData = doubleData;
    }

    @Override
    public String toString() {
        return "DemoData{" +
                "string='" + strData + '\'' +
                ", date=" + dateData +
                ", doubleData=" + doubleData +
                '}';
    }
}
