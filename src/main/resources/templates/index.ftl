<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>主页面</title>
    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/index.css" rel="stylesheet">
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
            <a class="navbar-brand" href="#">主页面</a>
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

<main class="benefits container">
    <!-- Example row of columns -->
    <div class="row" style="padding: 20px 60px 0 20px;">
        <div class="form-group">
            <label for="input_url"><h2>请输入请求的URL地址</h2></label>
            <div class="input-group">
                <div class="input-group-btn">
                    <button id="btn_method" type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"
                            aria-haspopup="true" aria-expanded="false" style="width: 85px">GET<span
                            class="caret"></span></button>
                    <ul class="dropdown-menu method">
                        <li><a href="#" onclick="set_method('GET')">GET</a></li>
                        <li><a href="#" onclick="set_method('POST')">POST</a></li>
                    </ul>
                </div>
                <input type="text" class="form-control" id="input_url" placeholder="URL">
                <div class="input-group-btn">
                    <button id="btn-method" class="btn btn-info ladda-button" data-style="slide-up" onclick="go();">
                        <span class="ladda-label">请求</span></button>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-6" style="padding-right: 60px;">
            <h2>header
                <button type="button" class="btn btn-info" style="float: right;" onclick="add_header();">新增</button>
            </h2>
            <p>
            <table class="table table-striped table-hover" style="margin-right: 33px;border:1px solid #eeeeee;">
                <thead>
                <tr>
                    <th onclick="all_header()" style="cursor:pointer;">#</th>
                    <th>Key</th>
                    <th>Value</th>
                    <th>取消</th>
                </tr>
                </thead>
                <tbody id="header_table">
                <tr t="header">
                    <th scope="row"><input class="cbx-header" type="checkbox"></th>
                    <td>
                        <div class="form-group group-input"><input type="text" onchange="key_change(this);">
                        </div>
                    </td>
                    <td>
                        <div class="form-group group-input"><input type="text" onchange="value_change(this);">
                        </div>
                    </td>
                    <td><span onclick="delete_tr(this);" class="glyphicon glyphicon-remove span_delete" aria-hidden="true"></span></td>
                </tr>
                </tbody>
            </table>
            </p>
        </div>
        <div class="col-md-6" style="padding-right: 60px;">
            <h2>params
                <button type="button" class="btn btn-info" style="float: right;" onclick="add_params();">新增
                </button>
            </h2>
            <p>
            <table class="table table-striped table-hover" style="margin-right: 33px;border:1px solid #eeeeee;">
                <thead>
                <tr>
                    <th onclick="all_params();" style="cursor:pointer;">#</th>
                    <th>Key</th>
                    <th>Value</th>
                    <th>取消</th>
                </tr>
                </thead>
                <tbody id="params_table">
                <tr t="params">
                    <th scope="row"><input class="cbx-params" type="checkbox"></th>
                    <td>
                        <div class="form-group group-input"><input type="text" onchange="key_change(this);">
                        </div>
                    </td>
                    <td>
                        <div class="form-group group-input"><input type="text" onchange="value_change(this);">
                        </div>
                    </td>
                    <td>
                        <span onclick="delete_tr(this);" class="glyphicon glyphicon-remove span_delete" aria-hidden="true"></span>
                    </td>
                </tr>
                </tbody>
            </table>
            </p>
        </div>
    </div>
    <div class="row" style="padding:10px 60px 0 15px;">
        <div class="panel panel-info">
            <div class="panel-heading">
                <h3 class="panel-title">返回结果:</h3>
            </div>
            <div class="panel-body">
                <pre id="show_result">

                </pre>
            </div>
        </div>

    </div>
</main>
<script src="/js/jquery-3.2.1.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/layer/layer.js"></script>
<script src="/js/ladda/spin.min.js"></script>
<script src="/js/ladda/ladda.min.js"></script>
<script>
    //常量
    var header_html = '<tr t="header"><th scope="row"><input class="cbx-header" type="checkbox"></th><td><div class="form-group group-input">' +
            '<input type="text" onchange="key_change(this);"></div></td><td><div class="form-group group-input">' +
            '<input type="text" onchange="value_change(this);"></div></td><td>' +
            '<span onclick="delete_tr(this);" class="glyphicon glyphicon-remove span_delete" aria-hidden="true"></span></td></tr>';

    var params_html = '<tr t="params"><th scope="row"><input class="cbx-params" type="checkbox"></th><td><div class="form-group group-input">' +
            '<input type="text" onchange="key_change(this);"></div></td><td><div class="form-group group-input">' +
            '<input type="text" onchange="value_change(this);"></div></td><td>' +
            '<span onclick="delete_tr(this);" class="glyphicon glyphicon-remove span_delete" aria-hidden="true"></span></td></tr>';

    var header_flag = true;

    var params_flag = true;

    var method = "GET";

    function key_change(d) {
        var key = $(d).val();
        var tr = $(d).parent().parent().parent();
        tr.attr('key', key);
    }

    function value_change(d) {
        var value = $(d).val();
        var tr = $(d).parent().parent().parent();
        tr.attr('value', value);
    }

    function delete_tr(d) {
        var tr = $(d).parent().parent();
        tr.remove();
    }

    function add_header() {
        $('#header_table').append(header_html);
    }

    function add_params() {
        $('#params_table').append(params_html);
    }

    function all_header() {
        $("input[class='cbx-header']").prop("checked", header_flag);//全选 或者 反选
        header_flag = !header_flag;
    }

    function all_params() {
        $("input[class='cbx-params']").prop("checked", params_flag);//全选 或者 反选
        params_flag = !params_flag;
    }

    function set_method(str) {
        method = str;
        $('#btn_method').empty().append(str + '<span class="caret"></span>')
    }

    function go() {
        var l = Ladda.create(document.getElementById('btn-method'));
        var ajaxData = {};
        var url = $('#input_url').val();
        if (!url) {
            layer.msg('请输入请求的链接值..');
            return;
        }
        ajaxData.url = url;
        ajaxData.method = method;
        ajaxData.headers = null;
        ajaxData.params = null;
        $('input[type="checkbox"]:checked').each(function (i) {
            console.log(i);
            var tr = $(this).parent().parent();
            var t = tr.attr('t');
            var key = tr.attr('key');
            var value = tr.attr('value');
            var obj = {};
            if (t == 'header') {
                if (ajaxData.headers == null) ajaxData.headers = {};
                ajaxData.headers[key] = value;
            }
            if (t == 'params') {
                if (ajaxData.params == null) ajaxData.params = {};
                ajaxData.params[key] = value;
            }
        });

        $.ajax({
            url: "/go",    //请求的url地址
            async: true, //请求是否异步，默认为异步，这也是ajax重要特性
            dataType: "JSON",
            data: {
                data: JSON.stringify(ajaxData)
            },    //参数值
            cache: false,
            type: "GET",   //请求方式
            beforeSend: function (XMLHttpRequest) {
                l.start();
            },
            success: function (data, textStatus) {
                if (!!data) {
                    if (data.code == 0) {
                        if (isJson(data.data)) {
                            $("#show_result").html(JSON.stringify(JSON.parse(data.data), null, 2));
                        } else {
                            $("#show_result").html(data.data);
                        }
                    } else {
                        layer.msg(data.msg);
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
    }

    function isJson(str) {
        if (/^[\],:{}\s]*$/.test(str.replace(/\\["\\\/bfnrtu]/g, '@').replace(/"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g, ']').replace(/(?:^|:|,)(?:\s*\[)+/g, ''))) {
            return true;
        } else {
            return false;
        }
    }

    $(function () {

    });

</script>
</body>
</html>