<!DOCTYPE html>
<html class="loginHtml">
<head>
    <meta charset="utf-8">
    <title>测试登录页</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="${rc.contextPath}/static/css/login2.css" media="all" />
    <link rel="stylesheet" href="${rc.contextPath}/static/layui/css/layui.css" media="all" />


</head>
<body>
<div class="login">
    <div class="login_title">
        <p>测试登录页</p>
    </div>
    <div class="login_main">
        <div class="main_left"></div>
        <div class="main_right">
            <div class="right_title">用户登录</div>
            <form class="layui-form" action="${rc.contextPath}/login/main" method="post">
                <div class="layui-form-item">
                    <img src="${rc.contextPath}/static/images/username.png" alt="">
                    <input type="text" class="layui-input" name="account" value="" placeholder="请输入账号" lay-verify="required"  autocomplete="off">
                </div>
                <div class="layui-form-item">
                    <img src="${rc.contextPath}/static/images/password.png" alt="">
                    <input type="password" class="layui-input" name="password" value="" placeholder="请输入密码" lay-verify="required"  autocomplete="off">
                </div>
                <div class="layui-form-item" style="margin-left: 50px">
                    <input type="checkbox" name="rememberMe" value="true" lay-skin="primary" checked title="记住帐号?">
                </div>
                <div class="layui-form-item" style="text-align: center">
                    <button class="layui-btn login_btn" lay-submit="" lay-filter="login">登录</button>
                </div>
            </form>
        </div>
    </div>
    <div class="login_footer">
        <p>建议浏览器：谷歌浏览器</p>
    </div>
</div>

<script type="text/javascript" src="${rc.contextPath}/static/layui/layui.js"></script>
<script type="text/javascript" src="${rc.contextPath}/static/js/jquery.min.js"></script>
<script type="text/javascript" src="${rc.contextPath}/static/js/jquery.bcat.bgswitcher.js"></script>
<script>

    if (window != top) {
        top.location.href = location.href;
    }

    layui.use(['layer', 'form'], function() {
        var layer = layui.layer,
            $ = layui.jquery,
            form = layui.form;

        // $(document).ready(function() {
        //     var srcBgArray = ["https://static.myp.com/chun.jpg",
        //         "https://static.myp.com/xia.jpg",
        //         "https://static.myp.com/qiu.jpg",
        //         "https://static.myp.com/dong.jpg"];
        //     $('#bg-body').bcatBGSwitcher({
        //         timeout:5000,
        //         urls: srcBgArray,
        //         alt: 'Full screen background image'
        //     });
        // });

        $("#mycode").on('click',function(){
            var t = Math.random();
            $("#mycode")[0].src="${rc.contextPath}/genCaptcha?t= "+t;
        });

        form.on('submit(login)', function(data) {
            var loadIndex = layer.load(2, {
                shade: [0.3, '#333']
            });
            if($('form').find('input[type="checkbox"]')[0].checked){
                data.field.rememberMe = true;
            }else{
                data.field.rememberMe = false;
            }
            $.post(data.form.action, data.field, function(res) {
                layer.close(loadIndex);
                if(res.success){
                    location.href="${rc.contextPath}/"+res.data.url;
                }else{
                    layer.msg(res.message);
                    $("#mycode").click();
                }
            });
            return false;
        });
    });
</script>
</body></html>