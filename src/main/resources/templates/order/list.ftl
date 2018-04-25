<html>
<head>
    <meta charset="UTF-8">
    <title>卖家商品列表</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
    <link rel="shortcut icon" href="/static/favicon.ico" type="image/x-icon"/>
</head>
<body>
<br>

<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="jumbotron well">
                <h1>
                    卖家后台管理系统
                </h1>
                <p>
                    给商户卖家提供的免费的商户后台系统哦！
                </p>
                <p>
                    <a class="btn btn-primary btn-small" href="javascript:void(0)">订单列表</a>
                </p>
            </div>

            <table class="table table-bordered">
                <thead>
                <tr class="success">
                    <th>订单id</th>
                    <th>姓名</th>
                    <th>手机号</th>
                    <th>地址</th>
                    <th>金额</th>
                    <th>订单状态</th>
                    <th>支付状态</th>
                    <th>创建时间</th>
                    <th colspan="2">操作</th>
                </tr>
                </thead>
                <tbody>
                  <#list orderDTOPage.content as orderDTO>
                  <tr>
                      <td>${orderDTO.orderId}</td>
                      <td>${orderDTO.buyerName}</td>
                      <td>${orderDTO.buyerPhone}</td>
                      <td>${orderDTO.buyerAddress}</td>
                      <td>${orderDTO.orderAmount}</td>
                      <td>${orderDTO.getOrderStatusEnum().message}</td>
                      <td>${orderDTO.payStatusEnum.message}</td>
                      <td>${orderDTO.createTime}</td>
                      <td>详情</td>
                      <td>
                        <#if orderDTO.getOrderStatusEnum().message != "已取消">
                            <a href="/sell/seller/order/cancel?orderid=${orderDTO.orderId}">取消</a>
                        </#if>
                      </td>
                  </tr>
                  </#list>
                </tbody>
            </table>
        </div>
    </div>

    <div class="col-md-12 column">
        <ul class="pagination pull-right">
            <#if currentPage lte 1>
            <li class="disabled">
                <a href="">上一页</a>
            </li>
            <#else >
             <li>
                 <a href="/sell/seller/order/list?page=${currentPage - 1}&size=${size}">上一页</a>
             </li>
            </#if>
            <#list 1..orderDTOPage.getTotalPages() as index >
                <#if currentPage == index>
                   <li class="disabled">
                       <a href="#">${index}</a>
                   </li>
                <#else>
            <li>
                <a href="/sell/seller/order/list?page=${index}&size=${size}">${index}</a>
            </li>
                </#if>
            </#list>

            <#if currentPage gte orderDTOPage.getTotalPages()>
                 <li class="disabled">
                     <a href="#">下一页</a>
                 </li>
            <#else >
             <li>
                 <a href="/sell/seller/order/list?page=${currentPage + 1}&size=${size}">下一页</a>
             </li>

            </#if>

        </ul>
    </div>
</div>
</body>
</html>
