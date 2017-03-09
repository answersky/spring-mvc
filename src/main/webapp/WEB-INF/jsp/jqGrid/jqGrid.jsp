<!DOCTYPE html>

<html lang="en">
<head>
  <!-- The jQuery library is a prerequisite for all jqSuite products -->
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.min.1.10.2.js"></script>
  <script type="text/ecmascript" src="${pageContext.request.contextPath}/js/jqGrid/grid.locale-en.js"></script>
  <!-- This is the Javascript file of jqGrid -->
  <script type="text/ecmascript" src="${pageContext.request.contextPath}/js/jqGrid/jquery.jqGrid.min.js"></script>
  <!-- This is the localization file of the grid controlling messages, labels, etc.
  <!-- A link to a jQuery UI ThemeRoller theme, more than 22 built-in and many more custom -->
  <link rel="stylesheet" type="text/css"
        href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.css">
  <!-- The link to the CSS that the grid needs -->
  <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/css/jqGrid/ui.jqgrid-bootstrap.css" />
  <script>
    $.jgrid.defaults.width = 780;
  </script>
  <script type="text/javascript"
          src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.js"></script>
  <meta charset="utf-8" />
  <title>jqGrid Loading Data - Million Rows from a REST service</title>
</head>
<body>
<div style="margin:0 auto; width: 50%;">
  <table id="jqGrid"></table>
  <div id="jqGridPager"></div>
</div>
<script type="text/javascript">
  $(document).ready(function () {

    var object='${object}';

    $("#jqGrid").jqGrid({
//      url: 'http://trirand.com/blog/phpjqgrid/examples/jsonp/getjsonp.php?callback=?&qwery=longorders',
      url: '${pageContext.request.contextPath}/jsonp/getjsonp?object='+object,
      mtype: "GET",
      styleUI : 'Bootstrap',
      datatype: "jsonp",
      colModel: [
        { label: 'Id', name: 'id', key: true, width: 75 },
        { label: 'name', name: 'name', width: 150 },
        { label: 'age', name: 'age', width: 150 },
      ],
      viewrecords: true,
      height: 250,
      rowNum: 6,
      pager: "#jqGridPager"
    });
  });

</script>


</body>
</html>