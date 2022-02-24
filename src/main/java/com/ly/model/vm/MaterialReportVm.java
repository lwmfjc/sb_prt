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
public class MaterialReportVm {

    //默认是按顺序从上往下解析的
    @ColumnWidth(25)
    @ExcelProperty( index = 0)
    private String no;

    @ColumnWidth(15)
    @ExcelProperty(  index = 1)
    private String type;

    @ColumnWidth(15)
    @ExcelProperty( index = 3)
    private String name;

    @ColumnWidth(15)
    @ExcelProperty( index = 9)
    private String certType;


}
