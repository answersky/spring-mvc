package com.java.service;

import com.alibaba.druid.support.json.JSONUtils;
import com.java.domain.City;
import com.java.utils.JsonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by liuf on 2017/3/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:config/application.xml"})
public class CityServiceTest {
    @Resource
    private CityService cityService;

    @Test
    public void findCityJson() throws Exception {
        List<City> cities=cityService.findCityJsonDB();
//        System.out.println(JsonUtils.objectToJson(cities));

        File file=new File("C:\\Users\\Administrator\\Desktop\\cityDB.json");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileWriter fw = null;
        try {
            fw = new FileWriter(file, true);
            fw.write(JsonUtils.objectToJson(cities));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fw != null)
                try {
                    fw.close();
                } catch (IOException e) {
                    throw new RuntimeException("关闭失败！");
                }
        }

    }



}