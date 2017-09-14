<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/24
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security" uri="/security" %>
<%@ taglib prefix="tf" uri="/function" %>
<html>
<head>
    <title>Exercise</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.min.1.10.2.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.js"></script>
    <style type="text/css">
        div.content{
            width: 1814px;
            height:1682px;
            margin: 0 auto;
            background: url("css/img/13.jpg") no-repeat;
        }
    </style>
</head>
<body>
<%@ include file="WEB-INF/jsp/common/nav.jsp"%>
<div class="content">
    <security:hasAnyPermission name="123">
        不显示
    </security:hasAnyPermission>
    <security:hasAnyPermission name="123">
        显示123
    </security:hasAnyPermission>

    <c:if test="${tf:validatePermission('update',1,2)}">
        ==============true
    </c:if>


</div>
</body>
</html>
