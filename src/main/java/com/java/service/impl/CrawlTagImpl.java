package com.java.service.impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.java.dao.ICrawlTagDao;
import com.java.domain.CrawlTag;
import com.java.domain.OfficePriceTag;
import com.java.service.ICrawlTagService;
import com.java.utils.Constant;
import com.mysql.jdbc.StringUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by liuf on 2016/8/25.
 */
@Service
public class CrawlTagImpl implements ICrawlTagService{
    @Resource
    private ICrawlTagDao crawlTagDao;

    @Override
    public CrawlTag findTagBySupplierId(int supplierId) {
        return crawlTagDao.findTagBySupplierId(supplierId);
    }

    @Override
    public List<OfficePriceTag> findPriceTagBySupplierId(int supplierId) {
        return crawlTagDao.findPriceTagBySupplierId(supplierId);
    }

    @Override
    public LinkedHashMap<String, List<String>> getTagOperations(CrawlTag crawlTag,String element) {
        if(StringUtils.isNullOrEmpty(element)){
            return null;
        }
        LinkedHashMap<String, List<String>> map=new LinkedHashMap();
        if(element.equals("pn")){
            String pn=crawlTag.getPn();
            map= (LinkedHashMap<String, List<String>>) JSONUtils.parse(pn);
        }

        if(element.equals("mfs")){
            String mfs=crawlTag.getMfs();
            map= (LinkedHashMap<String, List<String>>) JSONUtils.parse(mfs);
        }

        if(element.equals("description")){
            String description=crawlTag.getDescription();
            map= (LinkedHashMap<String, List<String>>) JSONUtils.parse(description);
        }

        if(element.equals("moq")){
            String moq=crawlTag.getMoq();
            map= (LinkedHashMap<String, List<String>>) JSONUtils.parse(moq);
        }

        if(element.equals("inventory")){
            String inventory=crawlTag.getInventory();
            map= (LinkedHashMap<String, List<String>>) JSONUtils.parse(inventory);
        }
        if(element.equals("amount")){
            String amount=crawlTag.getAmount();
            map= (LinkedHashMap<String, List<String>>) JSONUtils.parse(amount);
        }

        if(element.equals("price")){
            String price=crawlTag.getPrice();
            map= (LinkedHashMap<String, List<String>>) JSONUtils.parse(price);
        }
        return map;
    }

    @Override
    public LinkedHashMap<String, List<String>> getOfficePriceTagOperations(OfficePriceTag officePriceTag,String element) {
        if(StringUtils.isNullOrEmpty(element)){
            return null;
        }
        LinkedHashMap<String, List<String>> map=new LinkedHashMap<>();
        if(element.equals("officePrice")){
            String officePrice=officePriceTag.getOfficePrice();
            if(officePrice!=null)
            map= (LinkedHashMap<String, List<String>>) JSONUtils.parse(officePrice);
        }

        if(element.equals("amount")){
            String amount=officePriceTag.getAmount();
            if(amount!=null)
            map= (LinkedHashMap<String, List<String>>) JSONUtils.parse(amount);
        }

        if(element.equals("price")){
            String price=officePriceTag.getPrice();
            if(price!=null)
            map= (LinkedHashMap<String, List<String>>) JSONUtils.parse(price);
        }
        return map;
    }
}
