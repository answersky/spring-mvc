package com.java.service.impl;

import com.java.dao.CityDao;
import com.java.domain.City;
import com.java.service.CityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuf on 2017/3/5.
 */
@Service
public class CityServiceImpl implements CityService{
    @Resource
    private CityDao cityDao;


    @Override
    public List<City> findCityJson() {
        //获取所有的省份
        List<City> provinces=cityDao.findByParentId(0);
        if(provinces.size()>0){
            findChilds(provinces);
        }
        return provinces;
    }


    private void findChilds(List<City> cities){
        cities.forEach(city ->{
            Integer parentId=city.getId();
            List<City> cityList=cityDao.findByParentId(parentId);
            if(cityList!=null && cityList.size()>0){
                city.setChilds(cityList);
                findChilds(cityList);
            }
        });
    }


    @Override
    public List<City> findCityJsonDB() {
        List<City> list=cityDao.findAll();
        for(City city:list){
            Integer parentId=city.getId();
            List<Integer> childIds=cityDao.findIdByParentId(parentId);
            city.setChildIds(childIds);
        }
        return list;
    }


}
