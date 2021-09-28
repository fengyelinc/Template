var Page = function(param){
    this.nowParam = $.extend({
        model:"",
        url:"",
        limit:8,
        data:{},
        type:"POST",
        initHtml:function (data) {

        }
    }, param);
    this.reload = function (param) {
        this.nowParam = $.extend(this.nowParam, param);
        var curPage = 0;
        var _this = this;
        $.ajax({
            type: _this.nowParam.type,
            url: _this.nowParam.url,
            data:$.extend({limit:_this.nowParam.limit,page:curPage+1},_this.nowParam.data),
            dataType: "json",
            success: function(data){
                if(data.data.length!=0){
                    $("#"+_this.nowParam.model+"_cur").attr("data-page",curPage+1);
                    $("#"+_this.nowParam.model+"_cur").html("第"+(curPage+1)+"页");
                }
                $("#"+_this.nowParam.model+"_total").html("共"+data.count+"条");
                var dataHtml = "";
                $.each(data.data,function (i,n) {
                    dataHtml += _this.nowParam.initHtml(n);
                });
                $("#"+_this.nowParam.model+"_data").html(dataHtml);
            }
        });
    };
    this._next_page = function () {
        var curPage = parseInt($("#"+this.nowParam.model+"_cur").attr("data-page"));
        var _this = this;
        $.ajax({
            type: _this.nowParam.type,
            url: _this.nowParam.url,
            data:$.extend({limit:_this.nowParam.limit,page:curPage+1},_this.nowParam.data),
            dataType: "json",
            success: function(data){
                if(data.data.length!=0){
                    $("#"+_this.nowParam.model+"_cur").attr("data-page",curPage+1);
                    $("#"+_this.nowParam.model+"_cur").html("第"+(curPage+1)+"页");
                }else{
                    return;
                }
                $("#"+_this.nowParam.model+"_total").html("共"+data.count+"条");
                var dataHtml = "";
                $.each(data.data,function (i,n) {
                    dataHtml += _this.nowParam.initHtml(n);
                });
                $("#"+_this.nowParam.model+"_data").html(dataHtml);
            }
        });
    };
    this._pre_page = function () {
        var curPage = parseInt($("#"+this.nowParam.model+"_cur").attr("data-page"));
        if((curPage-1)==0){
            return;
        }
        var _this = this;
        $.ajax({
            type: _this.nowParam.type,
            url: _this.nowParam.url,
            data:$.extend({limit:_this.nowParam.limit,page:curPage-1},_this.nowParam.data),
            dataType: "json",
            success: function(data){
                if(data.data.length!=0){
                    $("#"+_this.nowParam.model+"_cur").attr("data-page",curPage-1);
                    $("#"+_this.nowParam.model+"_cur").html("第"+(curPage-1)+"页");
                }
                $("#"+_this.nowParam.model+"_total").html("共"+data.count+"条");
                var dataHtml = "";
                $.each(data.data,function (i,n) {
                    dataHtml += _this.nowParam.initHtml(n);
                });
                $("#"+_this.nowParam.model+"_data").html(dataHtml);
            }
        });
    };
    this._init_page = function () {
        var _this = this;
        this.reload(this.nowParam);
        $("#"+this.nowParam.model+"_pre").click(function () {
            _this._pre_page();
        });

        $("#"+this.nowParam.model+"_next").click(function () {
            _this._next_page();
        });
    }
}
