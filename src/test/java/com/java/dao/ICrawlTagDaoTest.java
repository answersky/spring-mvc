package com.java.dao;

import com.alibaba.druid.support.json.JSONUtils;
import com.google.common.collect.Lists;
import com.java.domain.CrawlTag;
import com.java.domain.OfficePriceTag;
import com.java.utils.Constant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by liuf on 2016/8/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:config/application.xml"})
public class ICrawlTagDaoTest {


    @Resource
    private ICrawlTagDao crawlTagDao;

    @Test
    public void insertTag() throws Exception {
        LinkedHashMap<String,List<String>> pnMap=new LinkedHashMap();
        List<String> operationList=new ArrayList();
        operationList.add(Constant.UpperCase);
        pnMap.put("div[class=product-top-info] div[class=field__item even]",operationList);


        LinkedHashMap<String,List<String>> mfsMap=new LinkedHashMap();
        List<String> mfsOperationList=new ArrayList();
        mfsMap.put("div[class=product-top-info] h4",mfsOperationList);

        LinkedHashMap<String,List<String>> desMap=new LinkedHashMap();
        List<String> desOperationList=new ArrayList();
        desMap.put("div[class=product-top-info-text] p",desOperationList);

        LinkedHashMap<String,List<String>> moqMap=new LinkedHashMap();
        List<String> moqOperationList=new ArrayList();
        moqOperationList.add(Constant.First);
        moqOperationList.add(Constant.Text);
        moqOperationList.add(Constant.Replace+"=>Minimum Order Qty: =>");
        moqOperationList.add(Constant.Trim);
        moqMap.put("ul[class=minmult_wrapper] li",moqOperationList);

        LinkedHashMap<String,List<String>> inventoryMap=new LinkedHashMap();
        List<String> inventoryOperationList=new ArrayList();
        inventoryOperationList.add(Constant.Text);
        inventoryOperationList.add(Constant.Trim);
        List<String> listCondition=Lists.newArrayList(Constant.Contains+"=>In-stock");
        inventoryOperationList.add(Constant.IF+"->"+JSONUtils.toJSONString(listCondition));
        inventoryOperationList.add(Constant.ReplaceAll);
        inventoryMap.put("div[class=product-top-info] p",inventoryOperationList);

        CrawlTag crawlTag=new CrawlTag();
        crawlTag.setSupplierId(28);
        crawlTag.setPn(JSONUtils.toJSONString(pnMap));
        crawlTag.setMfs(JSONUtils.toJSONString(mfsMap));
        crawlTag.setDescription(JSONUtils.toJSONString(desMap));
        crawlTag.setMoq(JSONUtils.toJSONString(moqMap));
        crawlTag.setInventory(JSONUtils.toJSONString(inventoryMap));
        crawlTagDao.insertTag(crawlTag);
    }

    @Test
    public void findTagBySupplierId() throws Exception {
        CrawlTag crawlTag=crawlTagDao.findTagBySupplierId(28);
        System.out.println(crawlTag);
    }

    @Test
    public void insertPriceTag() throws Exception {
        LinkedHashMap<String,List<String>> officePirceMap=new LinkedHashMap();
        List<String> officePirceList=new ArrayList();
        officePirceList.add(Constant.For);
        officePirceMap.put("table[class=sticky-enabled] tbody tr",officePirceList);

        LinkedHashMap<String,List<String>> amountMap=new LinkedHashMap();
        List<String> amountList=new ArrayList();
        amountList.add(Constant.First);
        amountList.add(Constant.Text);
        amountList.add(Constant.Split+"=>-=>0");
        amountList.add(Constant.ReplaceAll);
        amountMap.put("td",amountList);

        LinkedHashMap<String,List<String>> priceMap=new LinkedHashMap();
        List<String> priceList=new ArrayList();
        priceList.add(Constant.Last);
        priceList.add(Constant.Text);
        priceList.add(Constant.ReplaceAll);
        priceMap.put("td",priceList);

        OfficePriceTag officePriceTag=new OfficePriceTag();
        officePriceTag.setSupplierId(28);
        officePriceTag.setOfficePrice(JSONUtils.toJSONString(officePirceMap));
        officePriceTag.setAmount(JSONUtils.toJSONString(amountMap));
        officePriceTag.setPrice(JSONUtils.toJSONString(priceMap));
        crawlTagDao.insertPriceTag(officePriceTag);
    }

    @Test
    public void findPriceTagBySupplierId() throws Exception {
        List<OfficePriceTag> officePriceTag=crawlTagDao.findPriceTagBySupplierId(28);
        System.out.println(officePriceTag);
    }

}