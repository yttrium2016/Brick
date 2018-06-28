<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>跨域访问配置</title>
    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/table.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/ladda/ladda-themeless.min.css">
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top site-navbar">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/cors">跨域访问配置</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="#">随</a>
                </li>
                <li><a href="#">便</a>
                </li>
                <li><a href="#">开</a>
                </li>
                <li><a href="#">发</a>
                </li>
                <li><a href="#">的</a>
                </li>
                <li><a href="#">玩</a>
                </li>
                <li><a href="#">意</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container" style="margin-top: 50px">

    <div class="row marketing">
        <div class="bs-example" data-example-id="simple-table">
            <table class="table">
                <caption><#if table?? >${(table.table_show_name) !''}(${(table.table_name) ! ''})</#if>字段列表
                    <button id="btn-cors-add" class="btn btn-info btn-sm pull-right" style="margin-right: 10px;">新建</button>
                </caption>
                <thead>
                <tr>
                    <th>#</th>
                    <th width="30%">请求地址</th>
                    <th>实际地址</th>
                    <th>创建时间</th>
                    <th>删除</th>
                </tr>
                </thead>
                <tbody>
                <#if corsList?? && (corsList?size > 0) >
                    <#list corsList!"" as c>
                    <tr>
                        <th scope="row">${c_index+1}</th>
                        <td width="30%">${c.attr_name ! ''}</td>
                        <td>${c.url_name ! ''}</td>
                        <td>${c.create_date ! ''}</td>
                        <td>
                            <button class="btn btn-xs btn-danger" onclick="deleteCors('${c.attr_name ! ""}')">删除</button>
                        </td>
                    </tr>
                    </#list>
                </#if>
                </tbody>
            </table>
        </div>
    </div>


    <footer class="footer">
        <p>&copy; Copyright © 2017 yttrium2016.cn </p>
    </footer>

        <div id="add-cors-view" style="padding:30px 20px 20px 20px;width: 400px;display: none;">
            <form class="form-horizontal">
                <div class="form-group">
                    <label for="attrName" class="col-sm-4 control-label">请求名</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="attrName" placeholder="请求名">
                    </div>
                </div>
                <div class="form-group">
                    <label for="urlName" class="col-sm-4 control-label">实际地址</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="urlName" placeholder="实际地址">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-4 col-sm-8">
                        <button id="add-cors-submit" class="btn btn-primary ladda-button" data-style="slide-up">保存
                        </button>
                        <button id="add-cors-close" class="btn btn-danger" style="margin-left:20px;">关闭</button>
                    </div>
                </div>
            </form>
        </div>

</div>

<script src="/js/jquery-3.2.1.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/layer/layer.js"></script>
<script src="/js/ladda/spin.min.js"></script>
<script src="/js/ladda/ladda.min.js"></script>
<script>
    //常量
    var add_cors_view;

    function isJson(str) {
        if (/^[\],:{}\s]*$/.test(str.replace(/\\["\\\/bfnrtu]/g, '@').replace(/"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g, ']').replace(/(?:^|:|,)(?:\s*\[)+/g, ''))) {
            return true;
        } else {
            return false;
        }
    }

    function initBtnClick() {
        //新建表
        $('#btn-cors-add').on('click', function () {
            add_cors_view = layer.open({
                type: 1,
                title: false,
                closeBtn: 0,
                shadeClose: true,
                scrollbar: false,
                area: ['450px', 'auto'],
                skin: 'layui-layer-rim', //加上边框
                content: $('#add-cors-view')
            });
            return false;
        });

        //新建表的提交
        $('#add-cors-submit').click(function () {
            var l = Ladda.create(document.getElementById('add-cors-submit'));
            var attrName = $('#attrName').val();//请求地址
            var urlName = $('#urlName').val();//原地址
            if (!attrName || !urlName) {
                layer.msg('不能为空..');
                return false;
            }
            $.ajax({
                url: "/cors/create",    //请求的url地址
                async: true, //请求是否异步，默认为异步，这也是ajax重要特性
                dataType: "JSON",
                data: {
                    attrName: attrName,
                    urlName: urlName
                },    //参数值
                cache: false,
                type: "GET",   //请求方式
                beforeSend: function (XMLHttpRequest) {
                    l.start();
                },
                success: function (data, textStatus) {
                    layer.msg(data.msg);
                    if (data.code == 0){
                        if (!!add_cors_view) {
                            layer.close(add_cors_view);
                        }
                    }
                },
                complete: function (XMLHttpRequest, textStatus) {
                    l.stop();
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log("go Ajax error");
                }
            });
            return false;
        });

        //新建表的关闭页面
        $('#add-cors-close').click(function () {
            if (!!add_cors_view) {
                layer.close(add_cors_view);
            }
            return false;
        });

    }

    function deleteCors(attrName) {
        $.ajax({
            url: "/cors/delete",    //请求的url地址
            async: true, //请求是否异步，默认为异步，这也是ajax重要特性
            dataType: "JSON",
            data: {
                attrName: attrName
            },    //参数值
            cache: false,
            type: "GET",   //请求方式
            success: function (data, textStatus) {
                layer.msg(data.msg);
                if (data.code == 0){
                    if (!!add_cors_view) {
                        layer.close(add_cors_view);
                    }
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.log("go Ajax error");
            }
        });
    }

    $(function () {
        initBtnClick();
    });

</script>
</body>
</html>