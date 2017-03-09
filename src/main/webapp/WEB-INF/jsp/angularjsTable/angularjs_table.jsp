<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Angularjs</title>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/angular/angular.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.min.1.10.2.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.js"></script>
  <script src="${pageContext.request.contextPath}/js/myjs/app.js"></script>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.css" />
</head>
<body ng-controller="MainCtrl" ng-app="plunker">
<h2>Inline Edit</h2>
<table>
  <tr>
    <th>name</th>
    <th>phone</th>
    <th>操作</th>
  </tr>
  <tr ng-repeat="employee in employees">
    <td>
      <input type="text" id='txt_name_{{employee.id}}' ng-model="employee.name" class="inactive" readonly />
    </td>
    <td> <input type="text" ng-model="employee.phone" class="inactive" readonly /></td>
    <td><edit ng-Model="employee" ng-show="showEdit"><a>Edit</a></edit>
      <update ng-Model="employee" ng-show="!showEdit"><a>Update</a></update>
      <cancel ng-Model="employee" ng-show="!showEdit"> | <a>Cencel</a></cancel>
      | <delete ng-Model="employee"><a>Delete</a></delete>

    </td>
  </tr>
</table>
</body>
</html>
