(function ($) {
    $.fn.layuitable = function (options, fn) {
        var _this = this;
        var opt = {
            layui_config: {base: "", extend: {}, use: ['layer', 'form', 'table']},
            table_config: {
                elem: '#'+$(_this).attr("id"),
                height: $(parent.window).height() - 230,
                method: 'post',
                toolbar: '#toolbar', //开启头部工具栏，并为其绑定左侧模板
                limit: 20,
                where:{},
                page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                    layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'], //自定义分页布局
                    groups: 2, //只显示 1 个连续页码
                    first: "首页", //显示首页
                    last: "尾页", //显示尾页
                    limits: [3, 10, 20, 30]
                },
                width: $(parent.window).width() - 223
            },
            operate: {
                id: "id",
                add: {title: "", content: "", tips: ""},
                addRoot: {title: "", content: "", tips: ""},
                delete: {title: "", content: ""},
                enable: {title: "", content: ""},
                update: {title: "", content: ""},
                cancel: {title: "", content: ""},
                bmcancel:{title: "", content: ""},
                search: {title: ""},
                export: {title: "", content: ""},
                temporary_outgoing: {title: "", content: ""},
                print_temporary_in: {title: "", content: ""},
                print_temporary_out: {title: "", content: ""},
                buildSuit: {title: "", content: ""}
            },
            tollFunction:function (obj) {

            },
            tollbarFunction:function (obj) {

            }
        };
        $.extend(true, opt, options);

        layui.config({
            base: opt.layui_config.base
        }).extend(opt.layui_config.extend).use(opt.layui_config.use, function () {
            var layer = layui.layer,
                $ = layui.jquery,
                form = layui.form,
                table = layui.table,
                laydate = layui.laydate;
            table.render(opt.table_config);
            if (fn) {
                fn(layui, opt);
            }
            //搜索
            form.on("submit(searchForm)", function (data) {
                opt.table_config.where = data.field;
                table.reload('ytlj_table', opt.table_config);
                return false;
            });
            //搜索
            form.on("submit(reset)", function (data) {
                $("form")[0].reset();
                opt.table_config.where = {};
                debugger
                table.reload('ytlj_table', opt.table_config);
                return false;
            });

            table.on('toolbar('+$(_this).attr("id")+')', function (obj) {

                var checkStatus = table.checkStatus(obj.config.id);

                var idValue = '';
                //先判断是否有记录被选中，如有，则进入取值，如没有，则是新增、导出等按钮
                if (checkStatus.data.length >= 1) {
                    //再判断是否为删除，删除可支持批量删除，修改等操作，只需取第一个
                    if (obj.event == 'cancel'||obj.event == 'delete'||obj.event == 'temporary_outgoing'||obj.event == 'temporary_in'||obj.event == 'print_temporary_in'||obj.event == 'print_temporary_out' ||obj.event == 'buildSuit') {
                        //判断主键名称
                        if (opt.operate.id == 'jlbh') {
                            //循环取值，拼接多个记录编号
                            for (var i = 0; i < checkStatus.data.length; i++) {
                                if(obj.event == 'temporary_in'){
                                    var curData = checkStatus.data[i];
                                }
                                if (i == checkStatus.data.length - 1) {
                                    idValue += checkStatus.data[i].jlbh;
                                }else {
                                    idValue += (checkStatus.data[i].jlbh + ',');
                                }
                            }
                        } else {
                            for (var i = 0; i < checkStatus.data.length; i++) {
                                if (i == checkStatus.data.length - 1) {
                                    idValue += checkStatus.data[i].id;
                                }else {
                                    idValue += (checkStatus.data[i].id + ',');
                                }
                            }
                        }
                    } else {
                        //不是删除操作，只需要取选中列第一个值就好了
                        if (opt.operate.id == 'jlbh') {
                            idValue = checkStatus.data[0].jlbh;
                        } else {
                            idValue = checkStatus.data[0].id;
                        }
                    }

                }

                switch (obj.event) {
                    case 'add':
                        var addTypeIndex = layer.open({
                            title: opt.operate.add.title,
                            type: 2,
                            content: opt.operate.add.content,
                            success: function (layero, index) {
                                setTimeout(function () {
                                    layer.tips(opt.operate.add.tips, '.layui-layer-setwin .layui-layer-close', {
                                        tips: 3
                                    });
                                }, 500);
                            }
                        });
                        //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
                        $(window).resize(function () {
                            layer.full(addTypeIndex);
                        });
                        layer.full(addTypeIndex);
                        break;
                    case 'addRoot':
                        var addTypeIndex = layer.open({
                            title: opt.operate.addRoot.title,
                            type: 2,
                            content: opt.operate.addRoot.content,
                            success: function (layero, index) {
                                setTimeout(function () {
                                    layer.tips(opt.operate.addRoot.tips, '.layui-layer-setwin .layui-layer-close', {
                                        tips: 3
                                    });
                                }, 500);
                            }
                        });
                        //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
                        $(window).resize(function () {
                            layer.full(addTypeIndex);
                        });
                        layer.full(addTypeIndex);
                        break;
                    case 'delete':
                        if (checkStatus == null || checkStatus.data.length == 0) {
                            layer.msg('请至少勾选一条数据');
                            return;
                        }

                        layer.confirm('真的删除么', function (index) {
                            $.post(opt.operate.delete.content + idValue, {}, function (res) {
                                if (res.success) {
                                    layer.msg("删除成功", {time: 1000}, function () {
                                        table.reload('ytlj_table', opt.table_config);
                                    });
                                } else {
                                    layer.msg(res.message);
                                }
                            });
                            layer.close(index);
                        });
                        break;
                    case 'bmcancel':
                        if (checkStatus == null || checkStatus.data.length == 0) {
                            layer.msg('请至少勾选一条数据');
                            return;
                        }
                        layer.confirm('真的撤销吗', function (index) {
                            $.post(opt.operate.bmcancel.content + idValue, {}, function (res) {
                                if (res.success) {
                                    layer.msg("撤销成功", {time: 1000}, function () {
                                        table.reload('ytlj_table', opt.table_config);
                                    });
                                } else {
                                    layer.msg("撤销失败");
                                }
                            });
                            layer.close(index);
                        });
                        break;
                    case 'cancel':
                        if (checkStatus == null || checkStatus.data.length == 0) {
                            layer.msg('请至少勾选一条数据');
                            return;
                        }
                        layer.confirm('真的撤销么', function (index) {
                            $.post(opt.operate.cancel.content + idValue, {}, function (res) {
                                if (res.success) {
                                    layer.msg("撤销成功", {time: 1000}, function () {
                                        table.reload('ytlj_table', opt.table_config);
                                    });
                                } else {
                                    layer.msg("撤销失败");
                                }
                            });
                            layer.close(index);
                        });
                        break;
                    case 'enable':
                        if (checkStatus == null || checkStatus.data.length == 0) {
                            layer.msg('请勾选一条数据');
                            return;
                        }
                        layer.confirm('确定启用/关闭吗?', function (index) {

                            $.post(opt.operate.enable.content + idValue, {}, function (res) {
                                if (res.success) {
                                    layer.msg("启用/关闭成功", {time: 1000}, function () {
                                        table.reload('ytlj_table', opt.table_config);
                                    });
                                } else {
                                    layer.msg(res.message);
                                }
                            });
                            layer.close(index);
                        });
                        break;
                    case 'update':
                        if (checkStatus == null || checkStatus.data.length == 0) {
                            layer.msg('请勾选一条数据');
                            return;
                        }
                        var editIndex = layer.open({
                            title: opt.operate.update.title,
                            type: 2,
                            content: opt.operate.update.content + idValue,
                            success: function (layero, index) {
                                setTimeout(function () {
                                    layer.tips(opt.operate.update.tips, '.layui-layer-setwin .layui-layer-close', {
                                        tips: 3
                                    });
                                }, 500);
                            }
                        });
                        //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
                        $(window).resize(function () {
                            layer.full(editIndex);
                        });
                        layer.full(editIndex);
                        break;
                    case 'export':
                        window.location.href = opt.operate.export.content + $("#searchForm").serialize();
                        break;
                    case 'search':
                        debugger
                        //搜索
                        form.on("submit(searchForm)", function (data) {
                            opt.table_config.where = data.field;
                            table.reload('ytlj_table', opt.table_config);
                            return false;
                        });
                        break;
                    case 'buildSuit':
                        if (checkStatus == null || checkStatus.data.length == 0) {
                            layer.msg('请至少勾选一条数据');
                            return;
                        }

                        layer.confirm('确认生成套装？', function (index) {
                            $.post(opt.operate.buildSuit.content + idValue, {}, function (res) {
                                if (res.success) {
                                    layer.msg("成功", {time: 1000}, function () {
                                        table.reload('ytlj_table', opt.table_config);
                                    });
                                } else {
                                    layer.msg(res.message);
                                }
                            });
                            layer.close(index);
                        });
                        break;
                    default:
                        opt.tollbarFunction(obj);
                        break;
                }
            });


            // table.on('tool('+$(_this).attr("id")+')', function (obj) {
            //     opt.tollFunction(obj);
            // });
/*            table.on('tool(bar)', function (obj) {
                opt.tollFunction(obj);
            });*/



        });
    };

    $.fn.select = function (config) {
        var _this = this;
        var opt = {
            url:"",
            data:{},
            id:"id",
            value:"value"
        }
        $.extend(true, opt, config);
        layui.use(['layer', 'form', 'table'], function () {
            var layer = layui.layer,
                $ = layui.jquery,
                form = layui.form,
                table = layui.table;
                
            $.post(opt.url, config.data, function (data) {
                var _option = "<option value=''>请选择</option>";
                $.each(data.data,function (i,n) {
                    _option+='<option value="'+n[opt.id]+'">'+n[opt.value]+'</option>';
                });
                $(_this).html(_option);
                form.render('select');
            });

        });

    };
    $.fn.deptTreeSelect = function (config) {
        var current_obj = this;
        var opt = {
            input_name: "dept_id",
            click: function (d) {
                $(current_obj).after("<input type='hidden' name='" + opt.input_name + "' value='" + d.current.id + "'>");
            }
        };
        $.extend(true, opt, config);
        layui.config({
            base: App.root + '/static/zTree/module/'
        }).extend({
            treeSelect: 'treeSelect/treeSelect'
        });
        layui.use(['treeSelect', 'jquery'], function () {
            var treeSelect = layui.treeSelect;
            var deptTree = treeSelect.render({
                // 选择器
                elem: '#pidDIv',
                // 数据
                data: App.root + "/admin/system/dept/treedata",
                // 异步加载方式：get/post，默认get
                type: 'get',
                // 占位符
                placeholder: '请选择父级部门',
                // 是否开启搜索功能：true/false，默认false
                search: true,
                style: {
                    folder:
                        { // 父节点图标
                            enable: true // 是否开启：true/false
                        },
                    line:
                        { // 连接线
                            enable: true // 是否开启：true/false
                        }
                },
                // 点击回调
                click: function (d) {
                    opt.click(d);
                },
                // 加载完成后的回调函数
                success: function (d) {
                    var treeObj = treeSelect.zTree('pidfilter');
                    //刷新树结构
                    treeSelect.refresh('pidfilter');
                }
            });
        });
    };

    $.fn.selectDtree = function (config, _function) {
        var curObj = this;
        var _id = $(curObj).attr("id");
        var opt = {
            dtree_config: {
                elem: "#" + _id,
                url: App.root + "/admin/system/dept/dtreedata?pid=0",  //异步接口
                selectInputName: {
                    nodeId: "pid",
                    context: "pname"
                }
            }
        };
        $.extend(true, opt, config);
        layui.config({
            base: App.root + '/static/layui_ext/dtree/'
        }).extend({
            dtree: 'dtree'
        });
        layui.use(['dtree'], function () {
            var dtree = layui.dtree;
            if (_function != undefined && _function instanceof Function) {
                _function(dtree);
            }
            dtree.renderSelect(opt.dtree_config);

        });

    }


    $.filltable = function (data, dateIdStrs) {
        for (var id in data) {
            //时间特殊处理
            if (dateIdStrs && dateIdStrs.indexOf(id) != -1) {
                $("#" + id).val(layui.util.toDateString(data[id], 'yyyy-MM-dd HH:mm:ss'));
            } else {
                $("#" + id).val(data[id]);
                if("cw"==id){
                    $("#wpcfd").val(data.dwbmmc+data.csmc+data.cw);
                }
            }
        }
    }


    $.fillselect = function (selname, options) {
        var selvalue = $("select[name=" + selname).val();
        $("select[name=" + selname).find("option").remove();
        $("select[name=" + selname).append("<option value=''>-全部-</option>");
        for (var i in options) {
            $("select[name=" + selname).append("<option value=" + i + ">" + options[i] + "</option>");
        }
        $("select[name=" + selname).val(selvalue);
    }

    $.fn.room = function (config) {
        var _this = this;
        var opt = {
            lable1:"状态：",
            lable2:"在押人员:",
            url:"",
            data:{},
            name:"name",
            status:"status",
            personName:"personName"
        }
        $.extend(true, opt, config);
        layui.use('carousel', function(){
            $.post(opt.url, config.data, function (data) {
                var _html = "<div carousel-item>";
                var _item = "<div class=\"hw-div-item\">";
                var hasItemEnd = false;
                $.each(data.data,function (i,n) {
                    if(i==6){
                        hasItemEnd = true;
                        _item += "</div>"
                        _html += _item;
                        _item = "<div class=\"hw-div-item\">";
                    }else{
                        hasItemEnd = false;
                    }

                    _item+='<div class="hw-div-room">' +
                        '<div class="hw-div-room-tilte">'+n[opt.name]+'</div>'+
                        '<div class="hw-div-room-context">'+
                        '<div class="hw-div-room-context-list">';
                    if(n[opt.status]==0){
                        _item+='<label>'+opt.lable1+'</label><span class="not-use">空闲</span>';
                    }else{
                        _item+='<label>'+opt.lable1+'</label><span class="use">使用中</span>';
                    }
                    _item+='</div>'+
                        '<div class="hw-div-room-context-list">'+
                        '<label>'+opt.lable2+'</label><span>'+(n[opt.personName]==''?'暂无人员':n[opt.personName])+'</span>'+'' +
                        '</div>'+
                        '</div>'+
                        '</div>';

                });
                if(!hasItemEnd&&_item.indexOf("</div>")>=0){
                    _item += "</div>"
                    _html+= _item;
                }
                $(_this).html(_html+"</div>");
                var carousel = layui.carousel;
                //建造实例
                carousel.render({
                    elem: '#'+$(_this).attr("id")
                    ,width: '100%' //设置容器宽度
                    ,height:"240px"
                    ,arrow: 'always' //始终显示箭头
                    ,autoplay:false
                    //,anim: 'updown' //切换动画方式
                });
            });
        });
    };



    $.praseStrEmpty=function(str){
            if(!str || str=="undefined" || str=="null"){
                return "";
            }
            return str;
    }


    $.verifyUtil = {

        isPoneAvailable: function ($poneInput) {
            var myreg = /^[1][3,4,5,7,8][0-9]{9}$/;
            if (!myreg.test($poneInput)) {
                return false;
            } else {
                return true;
            }
        },

        isIdcardAvailable:function(idcode){
                // 加权因子
                var weight_factor = [7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2];
                // 校验码
                var check_code = ['1', '0', 'X' , '9', '8', '7', '6', '5', '4', '3', '2'];

                var code = idcode + "";
                var last = idcode[17];//最后一位

                var seventeen = code.substring(0,17);

                // ISO 7064:1983.MOD 11-2
                // 判断最后一位校验码是否正确
                var arr = seventeen.split("");
                var len = arr.length;
                var num = 0;
                for(var i = 0; i < len; i++){
                    num = num + arr[i] * weight_factor[i];
                }

                // 获取余数
                var resisue = num%11;
                var last_no = check_code[resisue];

                // 格式的正则
                // 正则思路
                /*
                第一位不可能是0
                第二位到第六位可以是0-9
                第七位到第十位是年份，所以七八位为19或者20
                十一位和十二位是月份，这两位是01-12之间的数值
                十三位和十四位是日期，是从01-31之间的数值
                十五，十六，十七都是数字0-9
                十八位可能是数字0-9，也可能是X
                */
                var idcard_patter = /^[1-9][0-9]{5}([1][9][0-9]{2}|[2][0][0|1][0-9])([0][1-9]|[1][0|1|2])([0][1-9]|[1|2][0-9]|[3][0|1])[0-9]{3}([0-9]|[X])$/;

                // 判断格式是否正确
                var format = idcard_patter.test(idcode);

                // 返回验证结果，校验码和格式同时正确才算是合法的身份证号码
                return last === last_no && format ? true : false;
            }



    }


    $.fn.serializeObject = function() {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function() {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [ o[this.name] ];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };

    $.getDictName = function (dictType,key) {
        var cookieKeyRoot = 'myp-dict-'+dictType+"-";
        var cookieKey = cookieKeyRoot+key;
        if($.cookie(cookieKey)!=null&&$.cookie(cookieKey)!=""){
            return $.cookie(cookieKey);
        }
        var val = "";
        $.ajaxSettings.async = false;
        $.get(App.root+"/admin/system/dict/selectDictByType",{type:dictType},function (res) {
            if(res.success==true){
                $.each(res.data,function (i,n) {
                    $.cookie(cookieKeyRoot+n.value, n.label, { expires: 1 });
                    if(n.value == key){
                        val=n.label;
                    }
                });
            }
        });
        $.ajaxSettings.async = true;
        return val;
    }
})(jQuery)
var _search_div_h = $($(".myp-search")[0]).height();
var _search_div_w = $($(".myp-search")[0]).width();
var _w = window.innerWidth;
$($(".myp-search-btn")[0]).css("position", "absolute");
$($(".myp-search-btn")[0]).css("top", (_search_div_h - 23) + "px");
var _s_right = _w - _search_div_w - 175;
if (_s_right < 0) {
    _s_right = 40;
}
$($(".myp-search-btn")[0]).css("right", _s_right + "px");

//layui数据表格合并行
function merge(res,columsName,columsIndex) {
    var data = res.data;
    var mergeIndex = 0;//定位需要添加合并属性的行数
    var mark = 1; //这里涉及到简单的运算，mark是计算每次需要合并的格子数
   /* var columsName = ['id','wysbm'];//需要合并的列名称
    var columsIndex = [1,11];//需要合并的列索引值*/

    for (var k = 0; k < columsName.length; k++) { //这里循环所有要合并的列
        var trArr = $(".layui-table-body>.layui-table").find("tr");//所有行
        for (var i = 1; i < res.data.length; i++) { //这里循环表格当前的数据
            var tdCurArr = trArr.eq(i).find("td").eq(columsIndex[k]);//获取当前行的当前列
            var tdPreArr = trArr.eq(mergeIndex).find("td").eq(columsIndex[k]);//获取相同列的第一列
            if (data[i][columsName[k]]!=null &&data[i][columsName[k]]!='undefined'&&data[i][columsName[k]]!=''&& (data[i][columsName[k]] === data[i-1][columsName[k]])) { //后一行的值与前一行的值做比较，相同就需要合并
                mark += 1;
                tdPreArr.each(function () {//相同列的第一列增加rowspan属性
                    $(this).attr("rowspan", mark);
                });
                tdCurArr.each(function () {//当前行隐藏
                    $(this).css("display", "none");
                });
            }else {
                mergeIndex = i;
                mark = 1;//一旦前后两行的值不一样了，那么需要合并的格子数mark就需要重新计算
            }
        }
        mergeIndex = 0;
        mark = 1;
    }
}
/*取窗口可视范围的高度*/
function getClientHeight()
{
    var clientHeight=0;
    if(document.body.clientHeight&&document.documentElement.clientHeight)
    {
        var clientHeight = (document.body.clientHeight<document.documentElement.clientHeight)?document.body.clientHeight:document.documentElement.clientHeight;
    }
    else
    {
        var clientHeight = (document.body.clientHeight>document.documentElement.clientHeight)?document.body.clientHeight:document.documentElement.clientHeight;
    }
    alert(clientHeight);
    return clientHeight;
}


//格式化时间
Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};

/*
    * 使用download.js 强制浏览器下载图片、视频等文件
    * @param {any} url url链接地址
    * @param {any} strFileName 文件名
    * @param {any} strMimeType 文件类型
     */
function downloadfile(url, strFileName, strMimeType) {
    var xmlHttp = null;
    if (window.ActiveXObject) {
        // IE6, IE5 浏览器执行代码
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    } else if (window.XMLHttpRequest) {
        // IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
        xmlHttp = new XMLHttpRequest();
    }
    //2.如果实例化成功，就调用open（）方法：
    if (xmlHttp != null) {
        xmlHttp.open("get", url, true);
        xmlHttp.responseType = 'blob';//关键
        xmlHttp.send();
        xmlHttp.onreadystatechange = doResult; //设置回调函数
    }
    function doResult() {
        if (xmlHttp.readyState == 4) { //4表示执行完成
            if (xmlHttp.status == 200) { //200表示执行成功
                download(xmlHttp.response, strFileName, strMimeType);
            }
        }
    }
};

/*
 * 根据文件名的尾缀 返回文件类型
 * @param {any} fileName 文件名
 */
function getFileType(fileName) {
    // 后缀获取
    let suffix = '';
    // 获取类型结果
    let result = '';
    try {
        const flieArr = fileName.split('.');
        suffix = flieArr[flieArr.length - 1];
    } catch (err) {
        suffix = '';
    }
    // fileName无后缀返回 false
    if (!suffix) { return false; }
    suffix = suffix.toLocaleLowerCase();
    // 图片格式
    const imglist = ['png', 'jpg', 'jpeg', 'bmp', 'gif'];
    // 进行图片匹配
    // result = imglist.find(item => item === suffix);
    // if (result) {
    //     return 'image';
    // }
    // 匹配txt
    // const txtlist = ['txt'];
    // result = txtlist.find(item => item === suffix);
    // if (result) {
    //     return 'txt';
    // }
    // // 匹配 excel
    // const excelist = ['xls', 'xlsx'];
    // result = excelist.find(item => item === suffix);
    // if (result) {
    //     return 'excel';
    // }
    // // 匹配 word
    // const wordlist = ['doc', 'docx'];
    // result = wordlist.find(item => item === suffix);
    // if (result) {
    //     return 'word';
    // }
    // // 匹配 pdf
    // const pdflist = ['pdf'];
    // result = pdflist.find(item => item === suffix);
    // if (result) {
    //     return 'pdf';
    // }
    // // 匹配 ppt
    // const pptlist = ['ppt', 'pptx'];
    // result = pptlist.find(item => item === suffix);
    // if (result) {
    //     return 'ppt';
    // }
    // // 匹配 视频
    // const videolist = ['mp4', 'm2v', 'mkv', 'rmvb', 'wmv', 'avi', 'flv', 'mov', 'm4v'];
    // result = videolist.find(item => item === suffix);
    // if (result) {
    //     return 'video';
    // }
    // // 匹配 音频
    // const radiolist = ['mp3', 'wav', 'wmv'];
    // result = radiolist.find(item => item === suffix);
    // if (result) {
    //     return 'radio';
    // }
    // 其他 文件类型
    return 'video';
};


function getAge(str){
   var year = new Date(str+" 12:00:00").getTime();
   var now = new Date().getTime();
   return parseInt((now - year)/1000/60/60/24/365);
}

/**
 * 通过url将图片转换为base64
 * @param url
 * @param ext
 * @param callback
 */
function getUrlBase64(url, ext, callback) {
    var canvas = document.createElement("canvas");
    var ctx = canvas.getContext("2d");
    var img = new Image;
    img.crossOrigin = 'Anonymous';
    img.src = url;
    img.onload = function () {
        canvas.height = 270;
        canvas.width = 200;
        ctx.drawImage(img, 0, 0, 60, 85);
        var dataURL = canvas.toDataURL("image/" + ext);
        callback.call(this, dataURL); //回掉函数获取Base64编码
        canvas = null;
    };
}