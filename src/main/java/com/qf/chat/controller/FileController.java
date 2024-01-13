package com.qf.chat.controller;

import cn.hutool.core.io.IoUtil;
import com.qf.chat.commons.returnresult.Resp;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.http.HttpResponse;
import java.util.UUID;

@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {
    @Value("${file.save}")
    private String filePath;
    @PostMapping("/upload")
    public Resp<String > upload(MultipartFile file){
        log.debug("进入了图片上载系统：{}",file);
        File f=new File(filePath);
        if(!f.exists()){
            f.mkdirs();
        }
        String fileName= UUID.randomUUID().toString();
        try(
                InputStream in = file.getInputStream();
                OutputStream out=new FileOutputStream(new File(f,fileName))
                ) {
            IoUtil.copy(in,out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Resp.succ(fileName);

    }

    @GetMapping("/download/{fileName}")
    public Resp download(@PathVariable String fileName, HttpServletResponse resp){
        log.debug("进入了图片下载系统：{}",fileName);
        try(
            InputStream in=new FileInputStream(new File(filePath,fileName));
            OutputStream out=resp.getOutputStream();
                ) {
            IoUtil.copy(in,out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Resp.succ();
    }
}
