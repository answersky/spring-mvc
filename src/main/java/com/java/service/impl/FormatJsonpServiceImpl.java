package com.java.service.impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.google.gson.Gson;
import com.java.domain.JsonpData;
import com.java.domain.User;
import com.java.service.IFormatJsonpService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by liuf on 2016/7/30.
 */
@Service
public class FormatJsonpServiceImpl implements IFormatJsonpService{

    public String getJsonpData(String callback,int page, String object, int rowNum) {
        Gson gson=new Gson();
        JsonpData jsonp=getJsonp(page,object, rowNum);
        String json= gson.toJson(jsonp);
        return callback+"("+json+")";
    }

    private JsonpData getJsonp(int page,String object,int rowNum){
        JsonpData jsonp=new JsonpData();
        try{
            //将object转成对象
            List<Object> objects= (List<Object>) JSONUtils.parse(object);
            if(objects!=null){
                int records=objects.size();
                jsonp.setRecords(records);
                jsonp.setPage(page);
                int totle = (records  +  rowNum  - 1) / rowNum;
                jsonp.setTotal(totle);
                List<Object> objList=getListByPage(page,rowNum,objects);
                jsonp.setRows(objList);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return jsonp;
    }

    private List<Object> getListByPage(int page,int rowNum,List<Object> objects){
        List<Object> list=new ArrayList<Object>();
        int start=(page-1)*rowNum;
        int end=page*rowNum-1;
        for(int i=start;i<=end;i++){
            if(i<=objects.size()-1){
                Object obj=objects.get(i);
                list.add(obj);
            }
        }
        return list;
    }
}
