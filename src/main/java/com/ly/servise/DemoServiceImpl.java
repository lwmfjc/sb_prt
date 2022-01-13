package com.ly.servise;

import com.alibaba.excel.EasyExcel;
import com.ly.dao.DemoDataMapper;
import com.ly.model.vm.DemoDataVm;
import com.ly.listener.DemoDataListener;
import com.ly.utils.DataCreateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
@Service
public class DemoServiceImpl implements DemoService{
    @Autowired
    private DemoDataMapper demoDataMapper;

    @Override
    public int batchInsert(List<DemoDataVm> demoDataVms) {
        demoDataMapper.batchInsert(demoDataVms);
        return 0;
    }

    @Override
    public void handleFile(InputStream inputStream) {
       EasyExcel.read(inputStream, DemoDataVm.class, new DemoDataListener(this)).sheet()
               .headRowNumber(3)
               .doRead();
    }

    @Override
    public void downloadFile(){
        EasyExcel.write("测试数据生成.xls", DemoDataVm.class)
                .sheet("模板1")
                .needHead(true)
                .doWrite(() -> {
                    // 分页查询数据
                    //return data();
                    return DataCreateUtils.data();
                });
    }
}
