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
            <a class="navbar-brand" href="/">主页面</a>
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
                <div class="input-group-btn">
                    <button id="btn_method" type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"
                            aria-haspopup="true" aria-expanded="false" style="width: 85px">GET
                    </button>
                    <#--<ul class="dropdown-menu method">-->
                        <#--<li><a href="#" onclick="set_method('GET')">GET</a></li>-->
                        <#--<li><a href="#" onclick="set_method('POST')">POST</a></li>-->
                    <#--</ul>-->
                </div>
                <select class="form-control" style="width:100%;">
                    <option value="1">a</option>
                    <option value="1">a</option>
                    <option value="1">a</option>
                    <option value="1">a</option>
                    <option value="1">a</option>
                    <option value="1">a</option>
                </select>
                <#--<div class="input-group-btn">-->
                    <#--<button id="btn-method" class="btn btn-info ladda-button" data-style="slide-up" onclick="go();">-->
                        <#--<span class="ladda-label">请求</span></button>-->
                <#--</div>-->
            </div>
        </div>

    </div>

    <div class="jumbotron">
        <h1>Jumbotron heading</h1>
        <p class="lead">Cras justo odio, dapibus ac facilisis in, egestas eget quam. Fusce dapibus, tellus ac cursus
            commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.</p>
        <p><a class="btn btn-lg btn-success" href="#" role="button">Sign up today</a></p>
    </div>

    <div class="row marketing">
        <div class="col-lg-6">
            <h4>Subheading</h4>
            <p>Donec id elit non mi porta gravida at eget metus. Maecenas faucibus mollis interdum.</p>

            <h4>Subheading</h4>
            <p>Morbi leo risus, porta ac consectetur ac, vestibulum at eros. Cras mattis consectetur purus sit amet
                fermentum.</p>

            <h4>Subheading</h4>
            <p>Maecenas sed diam eget risus varius blandit sit amet non magna.</p>
        </div>

        <div class="col-lg-6">
            <h4>Subheading</h4>
            <p>Donec id elit non mi porta gravida at eget metus. Maecenas faucibus mollis interdum.</p>

            <h4>Subheading</h4>
            <p>Morbi leo risus, porta ac consectetur ac, vestibulum at eros. Cras mattis consectetur purus sit amet
                fermentum.</p>

            <h4>Subheading</h4>
            <p>Maecenas sed diam eget risus varius blandit sit amet non magna.</p>
        </div>
    </div>


    <footer class="footer">
        <p>&copy; 2016 Company, Inc.</p>
    </footer>

</div> <!-- /container -->
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