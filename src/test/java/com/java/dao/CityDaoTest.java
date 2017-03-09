package com.java.dao;

import com.java.domain.City;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by liuf on 2017/3/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:config/application.xml"})
public class CityDaoTest {
    @Resource
    private CityDao cityDao;

    @Test
    public void findAllProvince() throws Exception {
        List<City> cities=cityDao.findByParentId(0);
        System.out.println(cities);
    }

    @Test
    public void testFindById(){
        List<City> cities=cityDao.findById(110100);
        System.out.println(cities);
    }

}