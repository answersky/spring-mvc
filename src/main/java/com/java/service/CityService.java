package com.java.service;

import com.java.domain.City;

import java.util.List;

/**
 * Created by liuf on 2017/3/5.
 */
public interface CityService {
    List<City> findCityJson();

    List<City> findCityJsonDB();
}
