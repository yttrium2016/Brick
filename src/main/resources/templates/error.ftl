<html>
<head>
    <meta charset="UTF-8">
    <title>动态error错误页面</title>
</head>
<body>
动态error错误页面
<p th:text="${error}"></p>
<p th:text="${status}"></p>
<p th:text="${message}"></p>
</body>
</html>