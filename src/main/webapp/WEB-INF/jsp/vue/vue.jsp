<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>vue.js</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/amazeui/amazeui.min.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.min.1.10.2.js"></script>
    <script src="${pageContext.request.contextPath}/js/amazeui/amazeui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/vue/vue.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/vue/vue-resource.js"></script>

</head>
<body>
<p>数据双向绑定</p>
<div id="app">
    <input v-model="message" placeholder="请输入...">
    <p>{{message}}</p>

    <p>单选</p>
    <input type="checkbox" id="checkbox" v-model="checked">
    <label for="checkbox">{{ checked }}</label>

    <p>多选框，绑定到一个数组</p>
    <input type="checkbox" id="jack" value="Jack" v-model="checkedNames">
    <label for="jack">Jack</label>
    <input type="checkbox" id="john" value="John" v-model="checkedNames">
    <label for="john">John</label>
    <input type="checkbox" id="mike" value="Mike" v-model="checkedNames">
    <label for="mike">Mike</label>
    <br>
    <span>Checked names: {{ checkedNames | json }}</span>

    <p>select</p>
    <select v-model="selected">
        <option selected>A</option>
        <option>B</option>
        <option>C</option>
    </select>
    <span>Selected: {{ selected }}</span>

    <p>过滤器</p>
    <span v-text="message | uppercase"></span>
</div>

<p>列表</p>
<div id="list">
    <ul>
        <li v-for="data in datas">
            {{ data.text }}
        </li>
    </ul>
</div>

<p>事件处理</p>
<div id="event">
    <p id="word">{{data}}</p>
    <input type="button" @click="upperCase" value="转大写">
    <input type="button" @click="lowerCase" value="转小写">
</div>

<p>http请求</p>
<div id="http">
    <ul>
        <li v-for="data in datas">
            {{data}}
        </li>
    </ul>
</div>
</body>
</html>
<script type="text/javascript">
    Vue.filter('uppercase',function (value) {
        return value.toUpperCase();
    });

    new Vue({
        el: '#app',
        data: {
            message: '',
            checked:false,
            checkedNames:[],
            selected:''
        }
    })

    new Vue({
        el: '#list',
        data: {
            datas: [
                { text: 'Learn JavaScript' },
                { text: 'Learn Vue.js' },
                { text: 'Build Something Awesome' }
            ]
        }
    });

    new Vue({
        el:'#event',
        data:{
            data:'hello word'
        },
        methods:{
            upperCase:function () {
                this.data=this.data.toUpperCase();
            },
            lowerCase:function () {
                this.data=this.data.toLowerCase();
            }
        }
    });

    new Vue({
        el:'#http',
        ready: function() {
            this.$http.get('/demo/findData').then(function(obj){
                        this.$set('datas', obj.data);
            },function(response){
                // 响应错误回调
            });
        },
        data:{
            datas:[]
        }
    });

    var progress = $.AMUI.progress;
    $(document).ready(function () {
        progress.start();
    });

    $(window).load(function () {
        progress.done(true);
    })
</script>