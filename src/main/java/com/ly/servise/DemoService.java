package com.ly.servise;
import com.ly.model.vm.DemoDataVm;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface DemoService {
    int batchInsert(List<DemoDataVm> demoDataVms);
    void handleFile(InputStream inputStream);
    void downloadFile(HttpServletResponse response) throws IOException;
    List<DemoDataVm> findAll();
}
