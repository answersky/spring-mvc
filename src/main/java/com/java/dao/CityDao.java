package com.java.dao;

import com.java.domain.City;

import java.util.List;

/**
 * Created by liuf on 2017/3/5.
 */
public interface CityDao {

    List<City> findByParentId(Integer parentId);

    List<City> findById(Integer id);

    List<City> findAll();

    List<Integer> findIdByParentId(Integer parentId);
}
