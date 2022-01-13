package com.ly.utils;

import com.alibaba.excel.util.ListUtils;
import com.ly.model.vm.DemoDataVm;

import java.util.Date;
import java.util.List;
import java.util.Random;

public class DataCreateUtils {
    public static List<DemoDataVm> data() {
        List<DemoDataVm> list = ListUtils.newArrayList();
        for (int i = 0; i < 10; i++) {
            DemoDataVm data = new DemoDataVm();
            data.setStrData1("字符串a" + i);
            data.setStrData2("字符串b" + i);
            data.setDateData(new Date());
            data.setDoubleData(new Random().nextDouble()*100);
            list.add(data);
        }
        return list;
    }
}
