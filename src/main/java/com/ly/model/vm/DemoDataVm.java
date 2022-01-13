package com.ly.model.vm;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@ToString
@Getter
@Setter
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


}
