package com.java.service;

/**
 * Created by liuf on 2016/7/30.
 */
public interface IFormatJsonpService {
    /**
     * 根据回调函数的名字以及json格式的数据来获取jsonp格式的结果
     * @param page  当前页
     * @param object   json格式的对象
     * @param rowNum   每页大小
     * @return  jsonp格式
     */
    String getJsonpData(String callback,int page,String object,int rowNum);
}
