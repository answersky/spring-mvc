<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.min.1.10.2.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.css" />
</head>
<body onunload="windowClose()" onmousedown="recordPosition(event)">
<table class="table table-bordered">
    <caption>用户信息</caption>
    <thead>
    <tr>
        <th>name</th>
        <th>age</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${userList}" var="user">
        <tr>
            <td>${user.name}</td>
            <td>${user.age}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="${pageContext.request.contextPath}/downCsv">下载</a>
<div>wwww</div>
<div>wwww</div>
<div>wwww</div>
<div>wwww</div>
<div>wwww</div>
<div>wwww</div>
<div>wwww</div>
<div>wwww</div>
<div>wwww</div>
<div>wwww</div>
<div>wwww</div>
<div>wwww</div>
<div>wwww</div>
<div>wwww</div>
<div>wwww</div>
<div>wwww</div>
<div>wwww</div>
<div>wwww</div>
<div>wwww</div>
<div>wwww</div>
<div>wwww</div>
<div>wwww</div>
<div>wwww</div>
<div>wwww</div>
<div>wwww</div>
<div>wwww</div>
<div>wwww</div>
<div>wwww</div>
<div>wwww</div>
<div>wwww</div>
<div>wwww</div>
<div>wwww</div>
<div>wwww</div>
<div>wwww</div>
<div>wwww</div>
</body>
</html>
<script type="text/javascript">
    function windowClose(){
        console.log("记录页面关闭时间");
        $.ajax({
            type: "post",
            url: "${pageContext.request.contextPath}/recordEndTime",
            contentType: "application/x-www-form-urlencoded;charset=utf-8",
            success: function (data) {
                console(data);
            },
            dataType: 'json'
        })
    }

//    document.onmousedown = recordPosition;
    function recordPosition(ev){
        ev = ev || window.event;
        var mousePos = mousePosition(ev);
        var content=ev.target;
        console.log(mousePos);
        console.log(content);
    }

    function mousePosition(ev){
        if(ev.pageX || ev.pageY){
            return {x:ev.pageX, y:ev.pageY};
        }
        return {
            x:ev.clientX + document.body.scrollLeft - document.body.clientLeft,
            y:ev.clientY + document.body.scrollTop - document.body.clientTop
        };
    }

    $(document).ready(function () {
        $("#downCsv").click(function () {
            $.ajax({
                type: "post",
                url: "${pageContext.request.contextPath}/downCsv",
                contentType: "application/x-www-form-urlencoded;charset=utf-8",
                success: function (data) {
                    console.log(data);
                },
                dataType: 'json'
            })
        })
    })

</script>