/**
 * 级联城市js插件
 * author  刘峰
 * date   2017/03/05
 */

//全局常量
var datas;
var secondCitys;
var thirdCitys;
$(document).ready(function () {
    //开始从json文件中读取数据
    $.getJSON('/demo/js/city.json',function(data){
        //创建jsonDB数据库
    //以后示例中都将使用init()方法创建的别名操作数据
        var jDB = jsonDB(data,'city').init('DB');
        //初始化省份
        getCity(data,'#firstCity');

        //第一个选择框
        $("#firstCity").change(function () {
            removeNextOption(this);
            var value=$("#firstCity").val();
            var result =DB.query('select childs from city where (id='+value+')');
            console.log("===========>"+result);
            var cityData=result[0].childs;
            secondCitys=cityData;
            getCity(cityData,'#secondCity');
        })

        //第二个选择框
        $("#secondCity").change(function () {
            removeNextOption(this);
            var value=$("#secondCity").val();
            console.log("===========>"+value);
            getNextCity(secondCitys,'#thridCity',value,1);
        })

        //第三个选择框
        $("#thridCity").change(function () {
            removeNextOption(this);
            var value=$("#thridCity").val();
            console.log("===========>"+value);
            getNextCity(thirdCitys,'#fourCity',value,2);
        })

    });
});

function getCity(data,obj) {
    for(var i=0;i<data.length;i++){
        var name=data[i].name;
        var id=data[i].id;
        $(obj).append("<option value='"+id+"'>"+name+"</option>");
    }
}

function removeNextOption(obj) {
    $(obj).nextAll('select').html("<option>-选择城市-</option>");
}

function getNextCity(data,obj,id,type) {
    for(var i=0;i<data.length;i++){
        var currentId=data[i].id;
        if(id==currentId){
            var cityData=data[i].childs;
            if(cityData!=null && cityData!=''){
                if(type==1){
                    thirdCitys=cityData;
                }
                for(var i=0;i<cityData.length;i++){
                    var name=cityData[i].name;
                    var nextId=cityData[i].id;
                    $(obj).append("<option value='"+nextId+"'>"+name+"</option>");
                }
            }
            return;
        }
    }
}