package com.java.dao;

import com.java.domain.CrawlTag;
import com.java.domain.OfficePriceTag;

import java.util.List;

/**
 * Created by liuf on 2016/8/24.
 */
public interface ICrawlTagDao {
    void insertTag(CrawlTag crawlTag);

    CrawlTag findTagBySupplierId(int supplierId);

    void insertPriceTag(OfficePriceTag officePriceTag);

    List<OfficePriceTag> findPriceTagBySupplierId(int supplierId);
}
