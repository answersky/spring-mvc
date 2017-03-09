package com.java.controller;

import com.google.gson.Gson;
import com.java.domain.User;
import com.java.service.IFormatJsonpService;
import com.java.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.spi.http.HttpContext;
import java.io.IOException;
import java.util.List;

/**
 * Created by liuf on 2016/7/29.
 */
@Controller
@RequestMapping("jsonp")
public class JqGridController {
    @Resource
    private IFormatJsonpService formatJsonpService;
    @Resource
    private UserService userService;

    @RequestMapping("getjsonp")
    public void getJsonp(Model model,String callback,int page,String object,int rows,HttpServletResponse response){
        response.setHeader("Content-type", "application/x-javascript;charset=utf-8");
        String jsonpData=formatJsonpService.getJsonpData(callback,page,object, rows);
        try {
            response.getWriter().print(jsonpData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping("jqGrid")
    public String jqGrid(Model model){
        List<User> list=userService.findUsers();
        Gson gson=new Gson();
        String object=gson.toJson(list);
        model.addAttribute("object",object);
        return "jqGrid/jqGrid";
    }
}
