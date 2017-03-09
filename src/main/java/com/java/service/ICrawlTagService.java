package com.java.service;

import com.java.domain.CrawlTag;
import com.java.domain.OfficePriceTag;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by liuf on 2016/8/25.
 */
public interface ICrawlTagService {
    /**
     * 根据supplierId来查询抓parser的tag
     * @param supplierId
     * @return
     */
    CrawlTag findTagBySupplierId(int supplierId);

    List<OfficePriceTag> findPriceTagBySupplierId(int supplierId);


    /**
     * 根据需要抓取的元素获取需要抓到该元素的标签以及操作
     * @param element  需要抓取的元素 pn/mfs/inventory等等
     * @param crawlTag crawltag对象
     * @return
     */
    LinkedHashMap<String,List<String>> getTagOperations(CrawlTag crawlTag,String element);

    /**
     * 根据需要抓取的元素获取需要抓到该元素的标签以及操作
     * @param officePriceTag  officePriceTag对象
     * @param element
     * @return
     */
    public LinkedHashMap<String, List<String>> getOfficePriceTagOperations(OfficePriceTag officePriceTag,String element);
}
