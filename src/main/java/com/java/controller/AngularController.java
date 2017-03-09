package com.java.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.google.gson.Gson;
import com.java.domain.Fruit;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuf on 2016/10/23.
 */
@RequestMapping("angular")
@Controller
public class AngularController {

    @RequestMapping("data")
    @ResponseBody
    public String getJsonData(){
        List<Fruit> list=new ArrayList<>();
        Fruit fruit=new Fruit();
        fruit.setName("apple");
        fruit.setColor("red");
        fruit.setWeight("12");

        Fruit fruit1=new Fruit();
        fruit1.setName("orange");
        fruit1.setColor("yellow");
        fruit1.setWeight("8");

        Fruit fruit2=new Fruit();
        fruit2.setName("watermelon");
        fruit2.setColor("green");
        fruit2.setWeight("25");
        list.add(fruit);
        list.add(fruit1);
        list.add(fruit2);
        Gson gson=new Gson();
        String json=gson.toJson(list);
        return json;
    }
}
