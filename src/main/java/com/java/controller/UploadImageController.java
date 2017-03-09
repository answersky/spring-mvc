package com.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuf on 2016/9/19.
 */
@Controller
@RequestMapping("upload")
public class UploadImageController {
    /**
     * 上传图片
     * @param upfile
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/images")
    public Map<String, Object> images (MultipartFile upfile, HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> params = new HashMap<String, Object>();
        String path=request.getContextPath();
        if (!upfile.isEmpty()) {
            FileOutputStream fos = null;
            try {
                byte[] bytes = upfile.getBytes();
                fos = new FileOutputStream("D:/myworkspace/"+path+"/src/main/webapp/js/ueditor/upload/image/"+upfile.getOriginalFilename());
                fos.write(bytes);
                String visitUrl="/js/ueditor/upload/image/"+upfile.getOriginalFilename();
                params.put("state", "SUCCESS");
                params.put("url", visitUrl);
                params.put("size", upfile.getSize());
                params.put("original", upfile.getOriginalFilename());
                params.put("type", upfile.getContentType());
            } catch (IOException e) {
                e.printStackTrace();
                params.put("state", "ERROR");
            } finally {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return params;
    }
}
