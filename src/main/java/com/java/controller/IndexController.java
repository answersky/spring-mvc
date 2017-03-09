package com.java.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by liuf on 2016/7/9.
 */
@Controller
public class IndexController {

    @RequestMapping("index")
    public String index(Model model){
        return "home/index";
    }

    @RequestMapping("angularjs")
    public String angularjs(Model model){
        return "angularjsTable/angularjs_table";
    }

    @RequestMapping("vue")
    public String vue(Model model, HttpServletRequest request){
       /* try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        System.out.println(this.getClass().getClassLoader().getResource("").getPath());
        System.out.println(request.getSession().getServletContext().getRealPath(""));
        System.out.println(request.getContextPath());
        return "vue/vue";
    }

    @RequestMapping("findData")
    @ResponseBody
    public String findData(){
        List<String> list= Lists.newArrayList("zhangsan","lisi","wanger");
        return JSONUtils.toJSONString(list);
    }

    @RequestMapping(value = "fileLoad",method = RequestMethod.POST)
    public String fileLoad(@RequestParam(value="file") MultipartFile file){
        if (!file.isEmpty()) {
            FileOutputStream fos = null;
            try {
                byte[] bytes = file.getBytes();
                fos = new FileOutputStream("/home/"+file.getOriginalFilename());
                fos.write(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "1";
    }

    @RequestMapping("ueditor")
    public String ueditor(){
        return "ueditor/index";
    }

    @RequestMapping("modal")
    public String modal(String modalName){
        return "modal/"+modalName;
    }

    @RequestMapping("jsCode")
    public String getJsCode(Model model) {
        String code = "<ins class='dcmads' style='display:inline-block;width:125px;height:436px'  data-dcm-placement='N4481.2145302QEGOO.CN/B9744504.132072204'  data-dcm-rendering-mode='script' data-dcm-https-only data-dcm-resettable-device-id='' data-dcm-click-tracker=''> <script src='https://www.googletagservices.com/dcm/dcmads.js'></script>";
        model.addAttribute("ad",code);
        return "home/ad";
    }
}
