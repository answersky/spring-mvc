<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/9/21
  Time: 9:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="left:-166px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close" style="margin-top:-10px;">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <form id="addMember" class="form-horizontal" method="post" enctype="multipart/form-data"
                  onSubmit="return false;">
                <div class="modal-body" style="padding-top: 0px;">
                   <!--此处是弹框的内容-->
                    </div>
                <div class="modal-footer">
                    <table style="float: right;">
                        <tr>
                            <td><button type="button" class="btn btn-primary btn-sm " onclick="saveModal()">确 定</button></td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td><button type="button" class="btn btn-default btn-sm" data-dismiss="modal">取 消</button></td>
                        </tr>
                    </table>

                </div>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript">
    var modalName=$("#bomb_button").attr("data-target").replace(/#/g, "");
    console.log(modalName);
    In.ready('bomb_box',function () {

    });


    $("div.modal").on('shown.bs.modal', function (e) {
        $.ajax({
            type: "post",
            url: "${pageContext.request.contextPath}/modal",
            data:{
                modalName:modalName
            },
            contentType: "application/x-www-form-urlencoded;charset=utf-8",
            success: function (data) {
                console.log(data);
                $("div.modal-body").html(data);
                //sendData函数主要是用于用户自己可以向弹框内传递参数
                sendData();
            }
        })
    });

</script>