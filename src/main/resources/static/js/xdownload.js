(function ($) {
    $.fn.xdownload = function (param) {
        var opt = {
            url: ""
            ,type: "POST"
            ,progress: function (progress) {

            }
            , error: function (errorMsg) {

            }
        }
        $.extend(true, opt, param);
        var xmlhttp = null;
        if (window.XMLHttpRequest) {// code for all new browsers
            xmlhttp = new XMLHttpRequest();
        } else if (window.ActiveXObject) {// code for IE5 and IE6
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
            seType = "blob";
        }
        if (xmlhttp != null) {
            xmlhttp.open(opt.type, opt.url, true);
            xmlhttp.responseType = "blob";  // 返回类型blob
            xmlhttp.onreadystatechange = function () {
                if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
                    var filename = "aa.zip";
                    if (typeof window.chrome !== 'undefined') {
                        // Chrome version
                        var link = document.createElement('a');
                        link.style.display = "none";
                        link.href = window.URL.createObjectURL(xmlhttp.response);
                        link.download = filename;
                        link.click();
                        URL.revokeObjectURL(link.href) // 释放URL 对象
                        document.body.removeChild(link)
                    } else if (typeof window.navigator.msSaveBlob !== 'undefined') {
                        // IE version
                        var blob = new Blob([xmlhttp.response], {type: 'application/force-download'});
                        window.navigator.msSaveBlob(blob, filename);
                    } else {
                        // Firefox version
                        var file = new File([xmlhttp.response], filename, {type: 'application/force-download'});
                        window.open(URL.createObjectURL(file));
                    }
                } else if (xmlhttp.readyState === 4 && xmlhttp.status === 400) {
                    var blob = xmlhttp.response;  //xhr.response -->blob 数据源
                    if (blob.size < 100) {      //此处是用来区分后台的数据源是文件、图片流，还是我们的告警信息，（如果是告警信息，size会很短）
                        var fr = new FileReader(); //FileReader可以读取Blob内容
                        fr.readAsText(blob); //二进制转换成text
                        fr.onload = function (e) {  //转换完成后，调用onload方法
                            var result = fr.result;  //result 转换的结果
                            opt.error(result);
                        }
                    }
                }
            };
            xmlhttp.addEventListener("progress", opt.progress, false);
            // 发送ajax请求
            xmlhttp.send();
        } else {
            alert("Your browser does not support XMLHTTP.");
        }
    }

    $.ytljajax = function (obj) {
        var url_ = obj.url;
        var data_ = obj.data;
        var type_ = obj.type;
        var success_ = obj.success;
        var error_ = obj.error;
        $.ajax({
            type:type_,
            async:false,
            url:url_ + "?" + data_,
            dataType:"jsonp",
            jsonp:"callback",
            jsonpCallback:"callback",
            success:success_,
            error:error_
        });
    }
})(jQuery)