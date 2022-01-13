package com.ly.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.util.MapUtils;
import com.ly.model.vm.DemoDataVm;
import com.ly.listener.DemoDataListener;
import com.ly.servise.DemoService;
import com.ly.servise.ISay;
import com.ly.utils.DataCreateUtils;
import com.sun.deploy.net.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Map;

@Slf4j
@RestController
@CrossOrigin(origins = {"http://localhost:3000", "null"})
public class HelloController {
    @Autowired
    private ISay iSay;
    @Autowired
    private DemoService demoService;

    @GetMapping("hello")
    public String hello() {
        log.info(iSay.sayWord());
        return iSay.sayWord();
    }

    /**
     * 文件上传
     * <p>1. 创建excel对应的实体对象 参照{@link DemoDataVm}
     * <p>2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link DemoDataListener}
     * <p>3. 直接读即可
     */
    @PostMapping("/upload")
    @ResponseBody
    public String upload(MultipartFile file) throws IOException {
        demoService.handleFile(file.getInputStream());
        return "success";
    }

    @GetMapping("/download")
    public void download(HttpServletResponse response,  DemoDataVm vm) throws IOException {
        log.info("receive params : strData2 ---"+vm.getStrData2());
        demoService.downloadFile(response);
    }


}
