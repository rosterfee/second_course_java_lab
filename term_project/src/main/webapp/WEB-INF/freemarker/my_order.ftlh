<#ftl encoding="utf-8">

<#import "header.ftlh" as header>

<@header.main>
<body class="bg-info">
<div class="container bg-light mt-5 rounded">
    <#if order??>
    <div class="table-responsive pt-3">
        <table class="table table-hover table-bordered">
            <thead>
            <tr>
                <th>Название</th>
                <th>Цена за 4 часа</th>
                <th>Удалить</th>
            </tr>
            </thead>
            <tbody id="cart">
            <tr>
                <td>${order.car.mark + " " + order.car.model}</td>
                <td>${order.car.price}</td>
                <td colspan="6" class="text-center" width="10%"><a href="/delete_order?order_id=${order.id}"><img src="../../../../../../../CarSharing/CarSharing/src/main/webapp/pictures/delete.jpg" alt="" style="width: 50%"></a></td>
            </tr>
            </tbody>
        </table>
    </div>

    <form action="/my_order" method="post">
        <label for="hours">Количество времени(ч):</label>
        <select name="hours" id="hours">
            <option value="4">4</option>
            <option value="8">8</option>
            <option value="12">12</option>
            <option value="24">24</option>
        </select>
        <input hidden name="order_id" value="${order.id}">
        <input hidden name="car_price" value="${order.car.price}">
        <button type="submit" id="order" class="btn btn-info mb-3">Оформить заказ</button>
    </form>

    <#else><h4>Вы еще не добавили машину в Ваш заказ</h4>

    </#if>
</div>
    <script>
        $(document).ready(function () {
            $('#hours').change(function () {
                $.ajax( "/total_price" )
                    .done(function(data) {
                        $(".total_price span").text(data)
                    })
                    .fail(function () {
                        alert("error")
                    });
            });
        })
    </script>
</body>
</@header.main>