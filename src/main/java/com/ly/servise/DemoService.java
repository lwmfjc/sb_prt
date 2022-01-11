package com.ly.servise;
import com.ly.model.vm.DemoDataVm;

import java.io.InputStream;
import java.util.List;

public interface DemoService {
    int batchInsert(List<DemoDataVm> demoDataVms);
    void handleFile(InputStream inputStream);
}
