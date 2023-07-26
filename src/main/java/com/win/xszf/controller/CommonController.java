package com.win.xszf.controller;

import com.win.xszf.common.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件上传
 */

@RestController
@RequestMapping("/common")
public class CommonController {


    private String basePath = "E:/Java/code/xszf/src/main/webapp/image/";
//    private String basePath = "D:/xiaosong/xszf/src/main/webapp/image/";
//    private String basePath = "C:/Users/yanguanghesama/Desktop/xszf/src/main/webapp/image/";

    /**
     * 文件上传
     *
     * @param file 必须与表单的name属性保持一致
     * @return
     */
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) {
        //原始文件名
        String originalFilename = file.getOriginalFilename();
        //截取后缀
        String substring = originalFilename.substring(originalFilename.lastIndexOf("."));

        //使用UUID重新生成文件名，防止文件覆盖
        String filename = UUID.randomUUID().toString().replace("-", "") + substring;
        //创建一个目录对象
        File dir = new File(basePath);
        //如果目录不存在
        if (!dir.exists()) {
            //目录不存在则创建新目录
            dir.mkdir();
        }
        try {
            file.transferTo(new File(basePath + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.success(filename);
    }


    /**
     * 文件下载
     *
     * @param name
     * @param response
     */
    @GetMapping("/download")
    public void download(String name, HttpServletResponse response) {

        try {
            //输入流读取文件内容
            System.out.println(basePath+name);
            FileInputStream fi = new FileInputStream(new File(basePath + name));

            //输出流，通过输出流将文件写回浏览器，在浏览器中展示图片
            ServletOutputStream sos = response.getOutputStream();

            response.setContentType("image/.jpeg");
            int len = 0;
            byte[] bytes = new byte[1024];

            while ((len = fi.read(bytes)) != -1) {
                sos.write(bytes, 0, len);
                sos.flush();
            }

            //关闭资源
            fi.close();
            sos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
