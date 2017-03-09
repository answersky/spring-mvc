package com.java.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.java.domain.User;
import com.java.service.IUserInfoService;
import com.java.service.UserService;
import com.java.utils.CookieUtils;
import com.java.utils.CsvUtils;
import org.apache.commons.io.output.FileWriterWithEncoding;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuf on 2016/7/9.
 */
@Controller
public class UserInfoController {
    @Resource
    private UserService userService;
    @Resource
    private IUserInfoService userInfoService;

    @RequestMapping("user")
    public String index(Model model){
        List<User> list=userService.findUsers();
        model.addAttribute("userList",list);
        return "user/index";
    }

    @RequestMapping("recordEndTime")
    @ResponseBody
    public String recordEndTime(HttpServletRequest request){
        userInfoService.recordETimeInfo(request,"JSESSIONID");
        return "1";
    }

    @RequestMapping("downCsv")
    public ModelAndView downCsv(HttpServletResponse response){
        List<User> list=userService.findUsers();
//        LinkedHashMap map = new LinkedHashMap();
//        map.put("name", "第一列");
//        map.put("age", "第二列");

        String path = "D:/home/csv/";
        String fileName = "csv文件导出";
        String row="第一列,第二列";
        List<String> titles=Lists.newArrayList("name","age");
        Gson gson=new Gson();
//        CsvUtils.exportCSV(gson.toJson(list), row, path, titles,response);
        List<Map<String,Object>> dataList = new ArrayList<>();
         Map<String, Object> map = null;
              for (User user : list) {
                  map = new HashMap<String, Object>();
                  map.put("name", user.getName());
                  map.put("age", user.getAge());
                  dataList.add(map);
             }
        try {
            OutputStream os = response.getOutputStream();
            CsvUtils.responseSetProperties("12",response);
            CsvUtils.doExport(dataList,row,"name,age",os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
