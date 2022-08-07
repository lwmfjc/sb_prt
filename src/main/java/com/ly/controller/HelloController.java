package com.ly.controller;

import com.alibaba.excel.util.MapUtils;
import com.ly.entity.User;
import com.ly.model.vm.DemoDataVm;
import com.ly.listener.DemoDataListener;
import com.ly.servise.DemoService;
import com.ly.servise.ISay;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class HelloController {
    String dirPath = System.getProperty("user.dir") + "\\hello\\";
    @Autowired
    private ISay iSay;
    @Autowired
    private DemoService demoService;

    @PostMapping("helloUser")
    @ResponseBody
    public User helloUser(@RequestBody User user) {
        System.out.println(user);
        return user;
    }
    @PostMapping("helloParams")
    public String helloParams(@RequestParam("name")String name) {
        System.out.println(name);
        return name;
    }
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
    public void download(HttpServletResponse response, DemoDataVm vm) throws IOException {
        log.info("receive params : strData2 ---" + vm.getStrData2());
        demoService.downloadFile(response);
    }

    /**
     * 文件上传
     * <p>1. 创建excel对应的实体对象 参照{@link DemoDataVm}
     * <p>2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link DemoDataListener}
     * <p>3. 直接读即可
     */
    @PostMapping("/uploadOfMine")
    @ResponseBody
    public String uploadOfMine(MultipartFile file) throws IOException {
        demoService.handleFileOfMine(file.getInputStream());
        return "success";
    }

    @PostMapping("/uploadFiles")
    @ResponseBody
    public String uploadFiles(MultipartFile file) throws IOException {
        File dirFile = new File(dirPath);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        InputStream inputStream = file.getInputStream();
        FileUtils.copyToFile(inputStream,
                new File(dirPath + file.getOriginalFilename()));
        inputStream.close();
        return "success";
    }

    /**
     * @param path 指想要下载的文件的路径
     * @param response
     * @功能描述 下载文件:将输入流中的数据循环写入到响应输出流中，而不是一次性读取到内存
     */
    @RequestMapping("/downloadLocal")
    public void downloadLocal(@RequestParam("fileName") String fileName, HttpServletResponse response) throws IOException {
        String filePath = dirPath + fileName;
        File file = new File(filePath);
        if(file.exists()) {
            // 读到流中
            InputStream inputStream = new FileInputStream(filePath);// 文件的存放路径

            System.out.println(fileName + "+++++++++++");
            response.reset();
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
            response.setContentType("application/octet-stream");
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));//设置下载的文件名称
            response.addHeader("Access-Control-Expose-Headers","content-disposition");
            //response.setHeader("fileName",fileName);

            ServletOutputStream outputStream = response.getOutputStream();
            byte[] b = new byte[1024];
            int len;
            //从输入流中读取一定数量的字节，并将其存储在缓冲区字节数组中，读到末尾返回-1
            while ((len = inputStream.read(b)) > 0) {
                outputStream.write(b, 0, len);
                outputStream.flush();
            }
            inputStream.close();
        }else {
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json; charset=utf-8");
            PrintWriter writer = response.getWriter();
            JSONObject o = new JSONObject();
            o.put("status", -1);
            writer.write(o.toString());
        }
    }

}
