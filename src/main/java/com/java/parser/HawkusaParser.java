package com.java.parser;

import com.alibaba.druid.support.json.JSONUtils;
import com.java.domain.CrawlTag;
import com.java.domain.OfficePriceTag;
import com.java.service.ICrawlTagService;
import com.java.utils.HtmlParse;
import com.java.utils.OperationUtil;
import com.java.utils.P;
import com.java.utils.Param;
import com.java.utils.S;
import com.mysql.jdbc.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
@Component
public class HawkusaParser {
    @Resource
    private ICrawlTagService crawlTagService;

    private final static String BASE_URL = "https://www.hawkusa.com/";

    public static void main(String[] args)  {
//        HawkusaParser hp = new HawkusaParser();
//        {
//            String url = "https://www.hawkusa.com/content/5205817-1";
//            try {
//                String html = Jsoup.connect(url).get().html();
//                List<Map<String, Object>> list=hp.parseDetailPage(html, url);
//                System.out.println(list);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }
        System.out.println("----------------------------------------------");
        {
            String url = "https://www.hawkusa.com/manufacturers/acme";
//            String html = CrawlBrower.getHtml(url);
            //			hp.parseListPage(html, url);
            //			TestParse.checkListMap(hp.parseListPage(html, url));
        }
        {
            //918
            String url = "https://www.hawkusa.com/content/3p18u3s24";
//            String html = TestParse.get(url);
//            Map<String, List<String>> result = hp.parseTaskUrlPage(html, url);
//            System.out.println(result);
        }
    }

     public List<Map<String, Object>> parseListPage(String html, String url) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Document doc = doc(html, url);
        if (doc == null) {
            return null;
        }
        doc.setBaseUri(BASE_URL);

        Elements els = doc.select("table[class=views-table cols-5] tbody tr");
        for (Element element : els) {
            P p = new P(element);
            //pn
            String pn = element.select("td[class=views-field views-field-title] h5 a").text().trim().toUpperCase();
            //			System.out.println(pn);
            p.pn(pn, false);

            //grabUrl
            String grabUrl = element.select("td[class=views-field views-field-title] h5 a").attr("abs:href");
            //			System.out.println(grabUrl);
            p.grabUrl(grabUrl, false);

            //mfs
            String mfs = element.select("td[class=views-field views-field-field-manufacturer] p").text().trim();
            //			System.out.println(mfs);
            p.mfs(mfs, false);
            p.supplierId("28", false);
            list.add(p.toMap());
        }
        return list;
    }

     public List<Map<String, Object>> parseDetailPage(String html, String url)  {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Document doc = doc(html, url);
        if (doc == null) {
            return null;
        }
        doc.setBaseUri(BASE_URL);

        P p = new P(doc);
        S s = new S(doc);

         CrawlTag crawlTag=crawlTagService.findTagBySupplierId(28);
        //pn
//        p.pn("div[class=product-top-info] div[class=field__item even]").toUpperCase();
        System.out.println(p.pn("div[class=product-top-info] div[class=field__item even]"));
         LinkedHashMap<String, List<String>> pnMap=crawlTagService.getTagOperations(crawlTag,"pn");
         pnMap.forEach((key,value)->{
             Elements elements=doc.select(key);
             String pn= OperationUtil.operation(elements,value);
             p.pn(pn,false);
         });

        //mfs
//        p.mfs("div[class=product-top-info] h4");
        System.out.println(p.mfs("div[class=product-top-info] h4"));
         LinkedHashMap<String, List<String>> mfsMap=crawlTagService.getTagOperations(crawlTag,"mfs");
         mfsMap.forEach((key,value)->{
             Elements elements=doc.select(key);
             String mfs= OperationUtil.operation(elements,value);
             p.mfs(mfs,false);
         });

        //description
//        p.description("div[class=product-top-info-text] p");
        System.out.println(p.description("div[class=product-top-info-text] p"));
         LinkedHashMap<String, List<String>> desMap=crawlTagService.getTagOperations(crawlTag,"description");
         desMap.forEach((key,value)->{
             Elements elements=doc.select(key);
             String des= OperationUtil.operation(elements,value);
             p.description(des,false);
         });

        //moq
        String moq1 = doc.select("ul[class=minmult_wrapper] li").first().text().replace("Minimum Order Qty: ", "")
                .trim();
        System.out.println(moq1);
         LinkedHashMap<String, List<String>> moqMap=crawlTagService.getTagOperations(crawlTag,"moq");
         Set<String> keyList=moqMap.keySet();
         String moq="";
         for(String key:keyList){
             Elements elements=doc.select(key);
             moq = OperationUtil.operation(elements,moqMap.get(key));
             s.moq(moq,false);
         }

        //inventory
        String str = doc.select("div[class=product-top-info] p").text().trim();
        System.out.println(str);

         LinkedHashMap<String, List<String>> inventoryMap=crawlTagService.getTagOperations(crawlTag,"inventory");
         inventoryMap.forEach((key,value)->{
             Elements elements=doc.select(key);
             String inventory= OperationUtil.operation(elements,value);
             if(inventory!=null){
                 s.inventory(inventory,false);
             }
            else{
                 s.inventory("0",false);
             }
         });


        //amount,price
         List<Map<String, Object>> prilist = new ArrayList<Map<String, Object>>();
         List<OfficePriceTag> officePriceTagList=crawlTagService.findPriceTagBySupplierId(28);

             for(OfficePriceTag officePriceTag:officePriceTagList){
                 LinkedHashMap<String, List<String>> offoicePriceMap=crawlTagService.getOfficePriceTagOperations(officePriceTag,"officePrice");
                 LinkedHashMap<String, List<String>> amountMap=crawlTagService.getOfficePriceTagOperations(officePriceTag,"amount");
                 LinkedHashMap<String, List<String>> priceMap=crawlTagService.getOfficePriceTagOperations(officePriceTag,"price");

                 //判断包含了多种情况的阶梯价
                 String ifCondition=officePriceTag.getIfCondition();
                 if(!("t2").equals(ifCondition)){
                     //获取key
                     Set<String> keys=offoicePriceMap.keySet();
                     for(String key:keys){
                         Elements trs=doc.select(key);
                         List<String> operations=offoicePriceMap.get(key);
                         prilist=OperationUtil.amountAndPriceOperation(trs,operations,amountMap,priceMap);
                     }
                 }else {
                     Map<String, Object> primap = new LinkedHashMap<String, Object>();
                     String amount = moq;
                     primap.put("amount", amount);

                     //price
                     priceMap.forEach((key,value)->{
                         Elements ele=doc.select(key);
                         String price=OperationUtil.operation(ele,value);
                         primap.put("priceStr", price);
                     });
                     prilist.add(primap);
                 }
             }


        /*if (doc.toString().contains("sticky-enabled")) {
            Elements trs = doc.select("table[class=sticky-enabled] tbody tr");

            for (Element element : trs) {
                Map<String, Object> primap = new LinkedHashMap<String, Object>();
                //amount
                if (!StringUtils.isNullOrEmpty(element.select("td").text())) {
                    String amount = element.select("td").first().text();
                    if (amount.contains("-")) {
                        amount = amount.split("-")[0].replaceAll("[^0-9]", "");
                    }
                    				System.out.println(amount);
                    primap.put("amount", amount);

                    //price
                    String price = element.select("td").last().text().replaceAll("[^0-9.]", "");
                    				System.out.println(price);
                    primap.put("priceStr", price);
                    prilist.add(primap);
                }

            }
        } else {
            Map<String, Object> primap = new LinkedHashMap<String, Object>();
            String amount = moq1;
            primap.put("amount", amount);

            //price
            String price = doc.select("div[class=product-main-price] div[class=field__item even]").text()
                    .replaceAll("[^0-9.]", "");
            			System.out.println(price);
            primap.put("priceStr", price);
            prilist.add(primap);
        }*/
        s.officalPrice(JSONUtils.toJSONString(prilist), false);
        s.currency("USD", false);

        p.addStore(s);
        p.supplierId("28", false);
        p.grabUrl(url, false);

        list.add(p.toMap());
        return list;
    }

    protected List<String> buildListPageUrl(String url, String maxPageNO) {
        List<String> list = new ArrayList<String>();
        maxPageNO = maxPageNO.replace(",", "").replace(" ", "");
        int maxNum = Integer.parseInt(maxPageNO);
        for (int index = 1; index <= maxNum; index++) {
            String fullUrl = getFullURl(url, index);
            list.add(fullUrl);
        }
        return list;
    }

    protected Document doc(String html, String url) {
        Document doc = HtmlParse.getDocFromString(html);
        if (doc != null) {
            doc.setBaseUri(url);
        }
        return doc;
    }

    //判断url类型
     public Map<String, List<String>> parseTaskUrlPage(String html, String url)  {

        List<String> re = new ArrayList();
        Map<String, List<String>> mapList = new HashMap();
        Document doc = HtmlParse.getDocFromString(html);
        doc.setBaseUri("https://www.hawkusa.com");
        //判断条件
        //第一个判断条件一般是列表页的表格属性
        Elements ListRequireOne = HtmlParse.getElement(doc, "table[class=views-table cols-5]");
        //第二个判断条件一般是详情页页的独有属性
        Elements ListRequireTwo = HtmlParse.getElement(doc, "div[class=product-top-info]");
        //第三个判断条件一般是918任务页的分类url属性
        Elements ListRequireThree = HtmlParse.getElement(doc, "table[class=views-view-grid]");
        if (ListRequireOne.size() > 0) {
            //判断是否进入到列表页
            re = buildListPageUrl(url, getMaxPageNO(url, doc));
            mapList.put(Param.TASK_TYPE_LIST_PAGE, re);
        } else if (ListRequireTwo.size() > 0) {
            //判断是否进入到详情页
            re.add(url);
            mapList.put(Param.TASK_TYPE_DETAIL_PAGE, re);
        } else if (!(ListRequireOne.size() > 0) && ListRequireThree.size() > 0) {
            //判断是否为918任务
            re = getCatalogTaskInfo(url, doc);
            mapList.put(Param.TASK_TYPE_CREATE_TASK, re);
        }
        return mapList;
    }

    //获取子分类url方法
    public List<String> getCatalogTaskInfo(String url, Document doc) {
        doc.setBaseUri("https://www.hawkusa.com");
        List<String> re = new ArrayList();
        //获取子分类url,存入918数据库
        Elements td = HtmlParse.getElement(doc, "span[class=field-content]");
        for (int i = 0; i < td.size(); i++) {
            String listUrl = td.get(i).select("a").eq(0).attr("abs:href");
            re.add(listUrl);
        }
        return re;
    }

     public String getFullURl(String url, int pageIndex) {
        if (pageIndex == 1) {
            return url;
        } else {
            String listUrl =
                    url + "?search_api_views_fulltext=&field_current_stock=All&&items_per_page=10&page=" + (pageIndex
                            - 1);
            return listUrl;
        }

    }

     public String getMaxPageNO(String url, Document doc) {

        String MaxPageNo = "";
        Elements span = HtmlParse.getElement(doc, "li[class=pager__item pager__item--last] a");
        if (span.size() > 0) {
            String text = span.attr("href").trim();
            if (text.contains("&page=")) {
                String[] a = text.split("&page=");
                MaxPageNo = a[1].trim().replaceAll("[^0-9]", "");
                MaxPageNo = "" + (Integer.valueOf(MaxPageNo) + 1);
            } else {
                MaxPageNo = "1";
            }
        } else {
            MaxPageNo = "1";
        }
        return MaxPageNo;
    }
}
