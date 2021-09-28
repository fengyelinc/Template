<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>无锡市公安局智能装备管理系统</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="icon" href="favicon.ico">
    <link rel="stylesheet" href="${rc.contextPath}/static/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="${rc.contextPath}/static/css/index.css" media="all" />

    <link rel="icon" type="image/jpg" sizes="144x144" href="${rc.contextPath}/static/images/jinghui.png"/>
    <style>
        .layui-main{
            margin: 0 5px !important;
        }
    </style>
</head>
<body class="main_body">
<div class="layui-layout layui-layout-admin">
    <!-- 顶部 -->
    <div class="layui-header header">
        <div class="layui-main mag0">
            <a href="javascript:;" class="logo" style="width: 400px;text-align: left">无锡市公安局智能装备管理系统</a>
            <!-- 显示/隐藏菜单 -->
            <#--<a href="javascript:;" class="seraph hideMenu icon-caidan"></a>-->
            <!-- 顶级菜单 -->
            <ul id="layui-nav-topLevelMenus" class="layui-nav topLevelMenus" pc>
            </ul>
            <!-- 顶部右侧菜单 -->
            <ul class="layui-nav top_menu">
                <li class="layui-nav-item" id="userInfo">
                    <a href="javascript:;"><img src="images/mrtx.jpg" class="layui-nav-img userAvatar" width="35" height="35">
<#--                        <cite class="adminName">${currentUser.name}</cite>-->
                    </a>
                    <dl class="layui-nav-child">
<#--                        <dd><a href="javascript:;" data-url="${rc.contextPath}/admin/system/user/changePassword"><i class="seraph icon-xiugai" data-icon="icon-xiugai"></i><cite>修改密码</cite></a></dd>-->
                        <dd pc><a href="javascript:;" class="changeSkin"><i class="layui-icon">&#xe61b;</i><cite>更换皮肤</cite></a></dd>
<#--                        <dd><a href="${rc.contextPath}/systemLogout" class="signOut"><i class="seraph icon-tuichu"></i><cite>退出</cite></a></dd>-->
                    </dl>
                </li>
            </ul>
        </div>
    </div>
    <!-- 左侧导航 -->
    <div class="layui-side layui-bg-black">

        <div class="navBar layui-side-scroll" id="navBar">
            <ul class="layui-nav layui-nav-tree">
            </ul>
        </div>
    </div>
    <!-- 右侧内容 -->
    <div class="layui-body layui-form">
        <div class="layui-tab mag0" lay-filter="bodyTab" id="top_tabs_box">
            <ul class="layui-tab-title top_tab" id="top_tabs">
                <li class="layui-this" lay-id=""><i class="layui-icon">&#xe68e;</i> <cite id="cite_name">首页</cite></li>
            </ul>
            <ul class="layui-nav closeBox">
                <li class="layui-nav-item">
                    <a href="javascript:;"><i class="layui-icon caozuo">&#xe643;</i> 页面操作</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" class="refresh refreshThis"><i class="layui-icon">&#x1002;</i> 刷新当前</a></dd>
                        <dd><a href="javascript:;" class="closePageOther"><i class="seraph icon-prohibit"></i> 关闭其他</a></dd>
                        <dd><a href="javascript:;" class="closePageAll"><i class="seraph icon-guanbi"></i> 关闭全部</a></dd>
                    </dl>
                </li>
            </ul>
            <div class="layui-tab-content clildFrame">
                <div class="layui-tab-item layui-show">
<#--                    <iframe src="${rc.contextPath}/zbgl/view/zbglZbsq/add"></iframe>-->
                </div>
            </div>
        </div>
    </div>
    <!-- 底部 -->
    <div class="layui-footer footer">
        <p><span>Copyright @2021 江苏人华警用装备有限公司</span>　　</p>
    </div>
</div>

<!-- 移动导航 -->
<div class="site-tree-mobile"><i class="layui-icon">&#xe602;</i></div>
<div class="site-mobile-shade"></div>
<script type="text/javascript" src="${rc.contextPath}/static/js/loadJS.js"></script>
<#--<script type="text/javascript" src="${rc.contextPath}/static/layui/layui.js"></script>-->
<script type="text/javascript" src="${rc.contextPath}/static/js/index.js"></script>
<script type="text/javascript" src="${rc.contextPath}/static/js/cache.js"></script>

</body>
</html>
