package com.java.service;

import com.java.domain.CrawlTag;
import com.java.parser.HawkusaParser;
import org.jsoup.Jsoup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by liuf on 2016/8/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:config/application.xml"})
public class ICrawlTagServiceTest {
    @Resource
    private ICrawlTagService crawlTagService;
    @Resource
    private HawkusaParser hawkusaParser;

    @Test
    public void findTagBySupplierId() throws Exception {

    }

    @Test
    public void getTagOperations() throws Exception {
        CrawlTag crawlTag=crawlTagService.findTagBySupplierId(28);
        LinkedHashMap<String, List<String>> list=crawlTagService.getTagOperations(crawlTag,"moq");
            list.forEach((key,value)->{
                System.out.println(key);
                value.forEach(operation->{

                    if(operation.contains("replace=>")){
                        String[] str=operation.split("=>");
                        System.out.println(str.length);
                        if(str.length==2){
                            System.out.println("replace('"+str[1]+"','')");
                        }
                    }
                    System.out.println(operation);
                });
            });
    }

    @Test
    public void testPaser(){
        String url = "https://www.hawkusa.com/content/5205817-1";
        try {
            String html = Jsoup.connect(url).timeout(30000).get().html();
            List<Map<String, Object>> list=hawkusaParser.parseDetailPage(html, url);
            System.out.println(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}