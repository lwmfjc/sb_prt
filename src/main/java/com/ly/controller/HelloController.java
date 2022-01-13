package com.ly.controller;

import com.ly.model.vm.DemoDataVm;
import com.ly.listener.DemoDataListener;
import com.ly.servise.DemoService;
import com.ly.servise.ISay;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@CrossOrigin(origins = {"http://localhost:3000", "null"})
public class HelloController {
    @Autowired
    private ISay iSay;
    @Autowired
    private DemoService demoService;

    @GetMapping("hello")
    public String hello(){
        log.info(iSay.sayWord());
        return iSay.sayWord();
    }
    /**
     * 文件上传
     * <p>1. 创建excel对应的实体对象 参照{@link DemoDataVm}
     * <p>2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link DemoDataListener}
     * <p>3. 直接读即可
     */
    @PostMapping("upload")
    @ResponseBody
    public String upload(MultipartFile file) throws IOException {
        demoService.handleFile(file.getInputStream());
        return "success";
    }

    @GetMapping("download")
    @ResponseBody
    public String download () throws IOException {
        System.out.println("开始生成文件");
        demoService.downloadFile( );
        return "success";
    }


}
