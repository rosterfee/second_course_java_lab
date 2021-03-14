<#ftl encoding="utf-8">

<#import "header.ftlh" as header>

<@header.main>
<body class="bg-info">
<div class="bg-light container text-left mt-5 rounded">
    <h4 class=" pt-2 text-center">Ваш заказ принят!</h4>
    <br>
    <h4>Можете забрать его по адресу: ${order.address}</h4>
    <h4>Время: ${order.timeOfOrderTaking}</h4>
    <h4>Авто: ${order.car.mark + " " + order.car.model}</h4>
    <a class="btn btn-info mb-3" href="/main">Вернуться на главную</a>
</div>
</body>
</@header.main>