<html>

<link rel="shortcut icon" href="/sell/static/favicon.ico" title="sml">
    <#include "../common/header.ftl">
<body>

<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="alert alert-dismissable alert-success">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                <h4>
                    成功
                </h4> <strong>${msg!''}</strong>
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
