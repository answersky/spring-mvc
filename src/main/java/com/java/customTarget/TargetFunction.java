package com.java.customTarget;

/**
 * @author liufeng
 * @version [1.0 , 2017/9/14]
 */
public class TargetFunction {

    public static Boolean validatePermission(String keyword, Integer menuId, Integer jobId) {
        boolean hasPermission = false;
      /*  //注入service
        WebApplicationContext wac= ContextLoader.getCurrentWebApplicationContext();
        ElementService elementService = (ElementService) wac.getBean("elementService");
        JobService jobService = (JobService) wac.getBean("jobService");

        //根据keyword去查询元素id
        Integer elementId = elementService.findIdByKeyword(keyword);
        if (elementId != null) {
            //根据elementId,jobId,menuId去判断是否有当前元素权限
            hasPermission = jobService.validElementPermission(jobId, menuId, elementId);
        }*/
        return hasPermission;
    }

}
