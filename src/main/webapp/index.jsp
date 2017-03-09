<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/amazeui/amazeui.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/date/bootstrap-datetimepicker.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.min.1.10.2.js"></script>
    <script src="${pageContext.request.contextPath}/js/amazeui/amazeui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/in.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/date/bootstrap-datetimepicker.js" charset="UTF-8"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/date/locales/bootstrap-datetimepicker.fr.js" charset="UTF-8"></script>
</head>
<body>
<h2>Hello World!</h2>
<ul>
    <li>
        <a href="${pageContext.request.contextPath}/vue">vuejs</a>
    </li>
</ul>

<form action="${pageContext.request.contextPath}/fileLoad"
      enctype="multipart/form-data" method="post">
    <input type="file" value="文件上传" name="file">
    <input type="submit" value="yes">
</form>

<button id="np-s" class="am-btn am-btn-primary">$.AMUI.progress.start();</button>
<button id="np-d" class="am-btn am-btn-success">$.AMUI.progress.done();</button>



<input type="button" id="bomb_button" data-toggle="modal" data-target="#myModal2" value="弹框">
<%@include file="modal.jsp"%>


<div class="container">
    <form action="" class="form-horizontal"  role="form">
        <fieldset>
            <legend>Test</legend>
            <div class="form-group">
                <label for="dtp_input1" class="col-md-2 control-label">DateTime Picking</label>
                <div class="input-group date form_datetime col-md-5" data-date="1979-09-16T05:25:07Z" data-date-format="dd MM yyyy - HH:ii p" data-link-field="dtp_input1">
                    <input class="form-control" size="16" type="text" value="" readonly>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                </div>
                <input type="hidden" id="dtp_input1" value="" /><br/>
            </div>
            <div class="form-group">
                <label for="dtp_input2" class="col-md-2 control-label">Date Picking</label>
                <div class="input-group date form_date col-md-5" data-date="" data-date-format="dd MM yyyy" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                    <input class="form-control" size="16" type="text" value="" readonly>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                </div>
                <input type="hidden" id="dtp_input2" value="" /><br/>
            </div>
            <div class="form-group">
                <label for="dtp_input3" class="col-md-2 control-label">Time Picking</label>
                <div class="input-group date form_time col-md-5" data-date="" data-date-format="hh:ii" data-link-field="dtp_input3" data-link-format="hh:ii">
                    <input class="form-control" size="16" type="text" value="" readonly>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
                </div>
                <input type="hidden" id="dtp_input3" value="" /><br/>
            </div>
        </fieldset>
    </form>
</div>

</body>
</html>
<script type="text/javascript">
    $('.form_datetime').datetimepicker({
        //language:  'fr',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        forceParse: 0,
        showMeridian: 1
    });
    $('.form_date').datetimepicker({
        language:  'fr',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });
    $('.form_time').datetimepicker({
        language:  'fr',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 1,
        minView: 0,
        maxView: 1,
        forceParse: 0
    });

    function saveModal() {
        console.log($("#content").text());
    }

    function sendData() {
        $("#content").append("www.qegoo.cn")
    }

    $(function(){
        var progress = $.AMUI.progress;

        $('#np-s').on('click', function() {
            progress.start();
        });

        $('#np-d').on('click', function() {
            progress.done();
        });
    });
</script>
