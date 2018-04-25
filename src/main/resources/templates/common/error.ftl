<html>
<head>
    <meta charset="UTF-8">
    <title>错误信息页</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="alert alert-dismissable alert-danger">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                <h4>
                    这里发生了一点小意外！
                </h4> <strong>${msg}</strong>
                <a href="${url}" class="alert-link">3秒后跳转哦～</a>
            </div>
        </div>
    </div>
</div>
</body>
<script>

    setTimeout('location.href="${url}"', 3000);


</script>
</html>
