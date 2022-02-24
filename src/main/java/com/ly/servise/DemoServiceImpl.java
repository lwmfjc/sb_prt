package com.ly.servise;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.util.MapUtils;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.ly.dao.DemoDataMapper;
import com.ly.model.vm.DemoDataVm;
import com.ly.listener.DemoDataListener;
import com.ly.model.vm.MaterialReportVm;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

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
    public void downloadFile(HttpServletResponse response) throws IOException {
        List<DemoDataVm> allDatas = findAll();
        for(DemoDataVm demoDataVm:allDatas){
            System.out.println(demoDataVm);
        }
        System.out.println("查询出了"+allDatas.size()+"条数据");
        ExcelWriter excelWriter=null;
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        try {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("测试", "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

            excelWriter = EasyExcel.write(response.getOutputStream(), DemoDataVm.class).build();
            WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
            /*excelWriter.write(DataCreateUtils.data("A"),writeSheet);
            System.out.println("导入数据A完毕");
            excelWriter.write(DataCreateUtils.data("B"),writeSheet);
            System.out.println("导入数据B完毕");*/
            excelWriter.write(allDatas,writeSheet);
        } catch (Exception e) {
            // 重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            Map<String, String> map = MapUtils.newHashMap();
            map.put("status", "failure");
            map.put("message", "下载文件失败" + e.getMessage());
            //log.info(new JSONObject(map).toString());
            response.getWriter().println(new JSONObject(map).toString());
        }finally {
            excelWriter.finish();
        }
    }

    @Override
    public void handleFileOfMine(InputStream inputStream) {

        EasyExcel.read(inputStream, MaterialReportVm.class, new AnalysisEventListener<MaterialReportVm>(){
            @Override
            public void invoke(MaterialReportVm materialReportVm, AnalysisContext analysisContext) {
                System.out.println("解析到一条数据");
                System.out.println(materialReportVm);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {

            }
        }).sheet()
                .headRowNumber(7)
                .doRead();
    }


    @Override
    public List<DemoDataVm> findAll() {
        return demoDataMapper.findAll();
    }
}
