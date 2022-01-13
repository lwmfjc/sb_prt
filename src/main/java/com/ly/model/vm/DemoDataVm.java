package com.ly.model.vm;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DemoDataVm {

    //默认是按顺序从上往下解析的
    @ColumnWidth(25)
    @ExcelProperty(value = "日期",index = 2)
    private Date dateData;

    @ColumnWidth(15)
    @ExcelProperty(value = "字符串1", index = 0)
    private String strData1;

    @ColumnWidth(15)
    @ExcelProperty(value = "字符串2", index = 1)
    private String strData2;

    @ExcelIgnore
    private String strData3;

    @ColumnWidth(15)
    @ExcelProperty(value = "数字数据", index = 3)
    private Double doubleData;

    public String getStrData3() {
        return strData3;
    }

    public void setStrData3(String strData3) {
        this.strData3 = strData3;
    }

    public String getStrData1() {
        return strData1;
    }

    public void setStrData1(String strData1) {
        this.strData1 = strData1;
    }

    public String getStrData2() {
        return strData2;
    }

    public void setStrData2(String strData2) {
        this.strData2 = strData2;
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


}
