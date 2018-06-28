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
                    <button id="btn-column-add" class="btn btn-info btn-sm pull-right" style="margin-right: 10px;">新建</button>
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

        <div id="add-table-view" style="padding:30px 20px 20px 20px;width: 400px;display: none;">
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
                        <button id="add-table-submit" class="btn btn-primary ladda-button" data-style="slide-up">保存
                        </button>
                        <button id="add-table-close" class="btn btn-danger" style="margin-left:20px;">关闭</button>
                    </div>
                </div>
            </form>
        </div>

        <div id="add-column-view" style="padding:30px 20px 20px 20px;width: 400px;display: none;">
            <form class="form-horizontal">
                <div class="form-group">
                    <label for="columnName" class="col-sm-4 control-label">表名(英文)</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="columnName" placeholder="字段名">
                    </div>
                </div>
                <div class="form-group">
                    <label for="columnShowName" class="col-sm-4 control-label">显示名(中文)</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="columnShowName" placeholder="显示名">
                    </div>
                </div>
                <div class="form-group">
                    <label for="dataType" class="col-sm-4 control-label">字段类型</label>
                    <div class="col-sm-8">
                        <select id="dataType" class="form-control" style="width:100%;">
                            <option value="" selected>请选择</option>
                        <#-- VARCHAR, INT, TEXT, DATETIME, DOUBLE; -->
                            <option value="VARCHAR">VARCHAR</option>
                            <option value="INT">INT</option>
                            <option value="TEXT">TEXT</option>
                            <option value="DATETIME">DATETIME</option>
                            <option value="DOUBLE">DOUBLE</option>
                        </select>
                    </div>
                </div>

            <#-- dataTypeLength -->
                <div class="form-group" id="div-dataTypeLength" style="display: none">
                    <label for="dataTypeLength" class="col-sm-4 control-label">字段长度</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="dataTypeLength" placeholder="显示名" onkeyup="value=value.replace(/[^\d]/g,'')">
                    </div>
                </div>

            <#-- defaultData -->
                <div class="form-group" id="div-defaultData" style="display: none">
                    <label for="defaultData" class="col-sm-4 control-label">默认值</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="defaultData" placeholder="显示名">
                    </div>
                </div>

            <#-- isNull -->
                <div class="form-group" id="div-isNull" >
                    <label for="isNull" class="col-sm-4 control-label">是否必填</label>
                    <div class="col-sm-8">
                        <select id="isNull" class="form-control" style="width:100%;">
                            <option value="0" selected>否</option>
                            <option value="1">是</option>
                        </select>
                    </div>
                </div>

            <#-- isDateUpdateNow -->
                <div class="form-group" id="div-isDateUpdateNow" style="display: none">
                    <label for="isDateUpdateNow" class="col-sm-4 control-label">自动更新</label>
                    <div class="col-sm-8">
                        <select id="isDateUpdateNow" class="form-control" style="width:100%;">
                            <option value="0" selected>否</option>
                            <option value="1">是</option>
                        </select>
                    </div>
                </div>

            <#-- isDateCreateNow -->
                <div class="form-group" id="div-isDateCreateNow" style="display: none">
                    <label for="isDateCreateNow" class="col-sm-4 control-label">创建更新</label>
                    <div class="col-sm-8">
                        <select id="isDateUpdateNow" class="form-control" style="width:100%;">
                            <option value="0" selected>否</option>
                            <option value="1">是</option>
                        </select>
                    </div>
                </div>


                <div class="form-group">
                    <div class="col-sm-offset-4 col-sm-8">
                        <button id="add-column-submit" class="btn btn-primary ladda-button" data-style="slide-up">保存
                        </button>
                        <button id="add-column-close" class="btn btn-danger" style="margin-left:20px;">关闭</button>
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
    var add_table_view; //新建表的layer框
    var add_column_view; //新建字段的layer框

    function isJson(str) {
        if (/^[\],:{}\s]*$/.test(str.replace(/\\["\\\/bfnrtu]/g, '@').replace(/"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g, ']').replace(/(?:^|:|,)(?:\s*\[)+/g, ''))) {
            return true;
        } else {
            return false;
        }
    }

    function getTableName() {
        var ar = window.location.href.split("#");
        var arr = ar[0].split("/");
        return arr[arr.length - 1] == 'table' ? '' : arr[arr.length - 1];
    }


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
                content: $('#add-table-view')
            });
            return false;
        });

        //新建字段
        $('#btn-column-add').on('click', function () {
            add_column_view = layer.open({
                type: 1,
                title: false,
                closeBtn: 0,
                shadeClose: true,
                scrollbar: false,
                area: '450px',
                skin: 'layui-layer-rim', //加上边框
                content: $('#add-column-view')
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
                    if (data.code == 0){
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

        //新建字段的提交
        $('#add-column-submit').click(function () {
            var l = Ladda.create(document.getElementById('add-column-submit'));
            var tableName = getTableName();
            var columnName = $('#columnName').val();
            var columnShowName = $('#columnShowName').val();
            var isNull = $('#isNull').val();
            var dataType = $('#dataType').val();
            var dataTypeLength = null;
            var defaultData = null;
            var isDateUpdateNow = null;
            var isDateCreateNow = null;
            switch (dataType) {
                case 'TEXT':
                    dataTypeLength = $('#dataTypeLength').val();
                    break;
                case 'VARCHAR':
                    dataTypeLength = $('#dataTypeLength').val();
                    defaultData = $('#defaultData').val();
                    break;
                case 'INT':
                    dataTypeLength = $('#dataTypeLength').val();
                    defaultData = $('#defaultData').val();
                    break;
                case 'DATETIME':
                    defaultData = $('#defaultData').val();
                    isDateUpdateNow = $('#isDateUpdateNow').val();
                    isDateCreateNow = $('#isDateCreateNow').val();
                    break;
                case 'DOUBLE':
                    defaultData = $('#defaultData').val();
                    break;
                default:
                    break
            }

            if (!tableName) {
                layer.msg('表名不能为空..');
                return false;
            }
            $.ajax({
                url: "/api/column/create",    //请求的url地址
                async: true, //请求是否异步，默认为异步，这也是ajax重要特性
                dataType: "JSON",
                data: {
                    tableName: tableName,
                    columnName: columnName,
                    columnShowName: columnShowName,
                    isNull: isNull,
                    dataType: dataType,
                    dataTypeLength: dataTypeLength,
                    defaultData: defaultData,
                    isDateUpdateNow: isDateUpdateNow,
                    isDateCreateNow: isDateCreateNow
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
                            location.reload();
                        });
                        if (!!add_column_view) {
                            layer.close(add_column_view);
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

        //新建字段的关闭页面
        $('#add-column-close').click(function () {
            if (!!add_column_view) {
                layer.close(add_column_view);
            }
            return false;
        });

    }

    $(function () {
        $('#table-select').on('change', function () {
            var value = $(this).val();
            if (!!value) {
                location.href = '/table/' + value;
            } else {
                location.href = '/table';
            }
        });

        var dataTypeLengthView = $('#div-dataTypeLength');
        var defaultDataView = $('#div-defaultData');
        var isDateUpdateNowView = $('#div-isDateUpdateNow');
        var isDateCreateNowView = $('#div-isDateCreateNow');
        var defaultDataInput = $('#defaultData');

        $('#dataType').on('change', function () {
        <#-- VARCHAR, INT, TEXT, DATETIME, DOUBLE; -->
            switch ($(this).val()) {
                case 'TEXT':
                    dataTypeLengthView.show();
                    defaultDataView.hide();
                    isDateUpdateNowView.hide();
                    isDateCreateNowView.hide();
                    break;
                case 'VARCHAR':
                    dataTypeLengthView.show();
                    defaultDataView.show();
                    isDateUpdateNowView.hide();
                    isDateCreateNowView.hide();
                    break;
                case 'INT':
                    dataTypeLengthView.show();
                    defaultDataView.show();
                    isDateUpdateNowView.hide();
                    isDateCreateNowView.hide();
                    //TODO
                    defaultDataInput.attr('onkeyup',"value=value.replace(/[^\\d]/g,'')");
                    break;
                case 'DATETIME':
                    dataTypeLengthView.hide();
                    defaultDataView.show();
                    isDateUpdateNowView.show();
                    isDateCreateNowView.show();
                    break;
                case 'DOUBLE':
                    dataTypeLengthView.hide();
                    defaultDataView.show();
                    isDateUpdateNowView.hide();
                    isDateCreateNowView.hide();
                    break;
                default:
                    dataTypeLengthView.hide();
                    defaultDataView.hide();
                    isDateUpdateNowView.hide();
                    isDateCreateNowView.hide();
                    break
            }

        });

        initBtnClick();

    });

</script>
</body>
</html>