<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app>
<head>
    <title>主页</title>
    <%@include file="../common/lib.jsp"%>
    <style type="text/css">
        #myText{
            margin-bottom: 50px;
        }
    </style>

</head>
<body>

<div id="firstName" ng-init="firstName='John'">

    <p>姓名为 <span ng-bind="firstName"></span></p>

</div>

<div id="name">
        <p>名字 : <input type="text" ng-model="name"></p>
        <h1>Hello {{name}}</h1>
</div>
<p>=======================================================================================================</p>

<div ng-controller="myCtrl">

    名: <input type="text" ng-model="firstName"><br>
    姓: <input type="text" ng-model="lastName"><br>
    <br>
    姓名: {{firstName + " " + lastName}}

</div>

<script>
    function myCtrl($scope) {
        $scope.firstName= "John";
        $scope.lastName= "Doe";
    }
</script>
<p>=======================================================================================================</p>
<!--数据绑定-->
<div ng-controller="countCtrl">
    <h2>价格计算器</h2>
    <span>数量</span>
    <input type="text" ng-model="amount">
    <span>*价格</span>
    <input type="text" ng-model="price">

    <span>总价：</span>{{amount*price}}
    </div>

<script>
    function countCtrl($scope) {
        $scope.price= 5.0;
        $scope.amount= 5;
    }
</script>
<p>=======================================================================================================</p>

<!--遍历-->
<div ng-init="names=['John','james','answer']">
    <span>输出所有的名称</span>
    <div ng-repeat="name in names">
        {{name}}
    </div>

</div>
<p>=======================================================================================================</p>

<!--验证-->
<form name="myForm" ng-init="texts='123@qq.com'">
    Email:
    <input type="email" name="myAddress" ng-model="texts">
    <span ng-show="myForm.myAddress.$error.email">不是一个合法的邮箱地址</span>
    <h1>状态</h1>
    {{myForm.myAddress.$valid}}
    {{myForm.myAddress.$error}}
    {{myForm.myAddress.$dirty}}
    {{myForm.myAddress.$touched}}
</form>
<p>=======================================================================================================</p>
<div ng-controller="data">
    <p>Total number of phones: {{phones.length}}</p>
    <p>{{word}}</p>
    <div class="span2" style="margin-bottom: 10px;">
        Search: <input ng-model="query">
    </div>
    <table class="table table-bordered table-hover">
    <thead>
    <tr>
        <th>name</th>
        <th>age</th>
        <th>score</th>
    </tr>
    </thead>
    <tbody>
    <tr ng-repeat='person in people | filter:query' class="success">
        <td>{{person.name}}</td>
        <td>{{person.age}}</td>
        <td>{{person.score}}</td>
    </tr>
    </tbody>
</table>
</div>

<p>=======================================================================================================</p>
<div ng-controller="http">
    <div class="span2" style="margin-bottom: 10px;">
        Search: <input ng-model="query">
    </div>
    <table class="table table-bordered table-hover">
        <thead>
        <tr>
            <th>index</th>
            <th>name</th>
            <th>color</th>
            <th>weight</th>
        </tr>
        </thead>
        <tbody>
        <tr ng-repeat="data in datas | filter:query | orderBy:'color'" class="success">
            <td>{{$index+1}}</td>
            <td>{{data.name}}</td>
            <td>{{data.color}}</td>
            <td>{{data.weight}}</td>
        </tr>
        </tbody>
    </table>
</div>

<script type="text/javascript">
    function data($scope) {
        $scope.phones=['苹果','三星','华为','小米'];
        $scope.word='hello word';
        $scope.people=[
                {
                    name:'zhangsan',
                    age:25,
                    score:98
                },
            {
                name:'zhaosi',
                age:24,
                score:90
            },
            {
                name:'wangwu',
                age:25,
                score:90
            },
            {
                name:'xuekaiqi',
                age:23,
                score:86
            }];
    }
    
    function http($scope,$http) {
        $http.get('/demo/angular/data').success(function (data) {
            $scope.datas=data;
        })
    }

</script>
<p>==========================笔记=============================================================================================</p>
<div id="myText" ng-controller="note">
<textarea style="width: 600px;height: 300px;" ng-model="text" data-ng-init="forbiddenWrite()">
{{text}}
</textarea>
    还剩下：{{100-text.length}}
    <input type="button" ng-click="remove()" value="清除">
    <input type="button" ng-click="save()" value="保存">
</div>
<script type="text/javascript">
    function note($scope) {
        $scope.remove=function () {
            $scope.text="";
        }

        $scope.save=function () {
            alert("note保存成功!");
        }

        $scope.forbiddenWrite=function () {

        }
    }
</script>
<%--<script type="text/javascript" src="/demo/jsCode"></script>--%>
</body>
</html>
<script>
    $(document).ready(function () {
        $.ajax({
            type: "get",
            url: "http://localhost:9000/jsonData",
            contentType: "application/x-www-form-urlencoded;charset=utf-8",
            jsonp: "callback",
            jsonpCallback: "jsonpCallback",
            dataType: 'jsonp',
            success: function (data) {
                console.log(data[0].id);
            }
        })
    })
</script>