<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>新建表</title>
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
            <a class="navbar-brand" href="/table">表页面</a>
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
    <div class="header clearfix">
        <div class="input-group" style="width:700px;">
            <div class="input-group">
                <select id="table-select" class="form-control" style="width:100%;">
                <#if table??>
                    <option value="" selected>请选择表</option>
                <#else >
                    <option value="">请选择表</option>
                </#if>
                <#if tableList?? && (tableList?size > 0) >
                    <#list tableList!"" as t>
                        <#if table?? && table.table_name == t.table_name>
                            <option value="${t.table_name ! ''}" selected>${t.table_show_name !''}
                                (${t.table_name ! ''})
                            </option>
                        <#else>
                            <option value="${t.table_name ! ''}">${t.table_show_name !''}(${t.table_name ! ''})</option>
                        </#if>
                    </#list>
                </#if>
                </select>
                <div class="input-group-btn">
                    <button id="btn-table-add" class="btn btn-info">
                        <span class="ladda-label">新建</span></button>
                </div>
            </div>
        </div>

    </div>

    <div class="row marketing">
        <div class="bs-example" data-example-id="simple-table">
            <table class="table">
                <caption><#if table?? >${(table.table_show_name) !''}(${(table.table_name) ! ''})</#if>字段列表
                    <button class="btn btn-info btn-sm pull-right" style="margin-right: 10px;">新建</button>
                </caption>
                <thead>
                <tr>
                    <th>#</th>
                    <th>字段名</th>
                    <th>字段显示名</th>
                    <th>字段类型</th>
                    <th>创建时间</th>
                    <th>修改</th>
                    <th>删除</th>
                </tr>
                </thead>
                <tbody>
                <#if columnList?? && (columnList?size > 0) >
                    <#list columnList!"" as c>
                    <tr>
                        <th scope="row">${c_index+1}</th>
                        <td>${c.column_name ! ''}</td>
                        <td>${c.column_show_name ! ''}</td>
                        <td>${c.data_type ! ''}</td>
                        <td>${c.create_date ! ''}</td>
                        <td>
                            <button class="btn btn-xs btn-primary">修改</button>
                        </td>
                        <td>
                            <button class="btn btn-xs btn-danger">删除</button>
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

    <div id="add-from" style="display: none;padding:30px 20px 20px 20px;width: 400px">
        <form class="form-horizontal">
            <div class="form-group">
                <label for="tableName" class="col-sm-4 control-label">表名(英文)</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" id="tableName" placeholder="表名">
                </div>
            </div>
            <div class="form-group">
                <label for="tableShowName" class="col-sm-4 control-label">显示名(中文)</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" id="tableShowName" placeholder="显示名">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-4 col-sm-8">
                    <button id="add-table-submit" class="btn btn-primary ladda-button" data-style="slide-up">保存</button>
                    <button id="add-table-close" class="btn btn-danger" style="margin-left:20px;">关闭</button>
                </div>
            </div>
        </form>
    </div>
</div> <!-- /container -->

<script src="/js/jquery-3.2.1.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/layer/layer.js"></script>
<script src="/js/ladda/spin.min.js"></script>
<script src="/js/ladda/ladda.min.js"></script>
<script>
    //常量
    var add_table_view; //新建表的layer框

    function isJson(str) {
        if (/^[\],:{}\s]*$/.test(str.replace(/\\["\\\/bfnrtu]/g, '@').replace(/"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g, ']').replace(/(?:^|:|,)(?:\s*\[)+/g, ''))) {
            return true;
        } else {
            return false;
        }
    }

    function getTableName() {
        var arr = window.location.href.split("/");
        return arr[arr.length - 1] == 'table' ? '' : arr[arr.length - 1];
    }

    var tableSelect = $('#table-select');

    function initBtnClick() {
        //新建表
        $('#btn-table-add').on('click', function () {
            add_table_view = layer.open({
                type: 1,
                title: false,
                closeBtn: 0,
                shadeClose: true,
                scrollbar: false,
                area: ['450px', 'auto'],
                skin: 'layui-layer-rim', //加上边框
                content: $('#add-from')
            });
            return false;
        });

        //新建表的提交
        $('#add-table-submit').click(function () {
            var l = Ladda.create(document.getElementById('add-table-submit'));
            var tableName = $('#tableName').val();
            var tableShowName = $('#tableShowName').val();
            if (!tableName) {
                layer.msg('表名不能为空..');
                return false;
            }
            $.ajax({
                url: "/api/table/create",    //请求的url地址
                async: true, //请求是否异步，默认为异步，这也是ajax重要特性
                dataType: "JSON",
                data: {
                    tableName: tableName,
                    tableShowName: tableShowName
                },    //参数值
                cache: false,
                type: "GET",   //请求方式
                beforeSend: function (XMLHttpRequest) {
                    l.start();
                },
                success: function (data, textStatus) {
                    if (data.code == 0) {
                        layer.msg(data.msg, {
                            time: 1800 //2秒关闭（如果不配置，默认是3秒）
                        }, function () {
                            location.href = '/table/' + tableName;
                        });
                        if (!!add_table_view) {
                            layer.close(add_table_view);
                        }
                    } else {
                        layer.msg(data.msg);
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
        $('#add-table-close').click(function () {
            if (!!add_table_view) {
                layer.close(add_table_view);
            }
            return false;
        });

    }

    $(function () {
        var tableName = getTableName();
        if (!tableName) {
        }

        tableSelect.on('change', function () {
            var value = $(this).val();
            if (!!value) {
                location.href = '/table/' + value;
            } else {
                location.href = '/table';
            }
        });

        initBtnClick();

    });

</script>
</body>
</html>