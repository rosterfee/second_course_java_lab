<#ftl encoding="utf-8">

<#import "header.ftlh" as header>

<@header.main>
<body class="bg-info">
<div class="container bg-light mt-5 rounded">
    <h2 class="text-center">Оплата</h2>
    <h4>Итого к оплате: ${sum} руб.</h4>
    <form action="/pay_offer" method="post">
        <label for="date">Выберете дату, когда вы хотите забрать авто</label>
        <input style="width: 200px" type="datetime-local" class="form-control" id="date" name="date" placeholder="Дата"
               required>

    <div class="mt-3">
        <h3>Форма оплаты</h3>
        <ul class="nav nav-tabs">
            <!-- Первая вкладка (активная) -->
            <li class="nav-item">
                <a class="nav-link active" data-toggle="tab" href="#irl">При получении</a>
            </li>
            <!-- Вторая вкладка -->
            <li class="nav-item">
                <a class="nav-link" data-toggle="tab" href="#bank_card">Онлайн</a>
            </li>
        </ul>
        <div class="tab-content">
            <div class="tab-pane active" id="irl">
            </div>
            <div class="container mt-3 tab-pane" id="bank_card">
                <div class="row ">
                    <div class="col-md-4 col-md-offset-4">

                        <div class="credit-card-div">
                            <div class="panel panel-default">
                                <div class="panel-heading">

                                    <div class="row ">
                                        <div class="col-md-12">
                                            <input type="text" class="form-control" placeholder="Enter Card Number"/>
                                        </div>
                                    </div>

                                    <div class="row ">
                                        <div class="col-md-3 col-sm-3 col-xs-3">
                                            <span class="help-block text-muted small-font"> Expiry Month</span>
                                            <input type="text" class="form-control" placeholder="MM"/>
                                        </div>
                                        <div class="col-md-3 col-sm-3 col-xs-3">
                                            <span class="help-block text-muted small-font"> Expiry Year</span>
                                            <input type="text" class="form-control" placeholder="YY"/>
                                        </div>
                                        <div class="col-md-3 col-sm-3 col-xs-3">
                                            <span class="help-block text-muted small-font"> CCV</span>
                                            <input type="text" class="form-control" placeholder="CCV"/>
                                        </div>
                                        <div class="col-md-3 col-sm-3 col-xs-3">
                                            <img src="https://bootstraptema.ru/snippets/form/2016/form-card/card.png"
                                                 class="img-rounded"/>
                                        </div>
                                    </div>

                                    <div class="row ">
                                        <div class="col-md-12 pad-adjust">

                                            <input type="text" class="form-control" placeholder="Name On The Card"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="mt-2">
        <label for="address">Выберете адрес, где заберете авто:</label>
        <select name="address" id="address" required>
            <#if kazan??>
                <option value="ул.Чистопольская,49">ул.Чистопольская,49</option>
                <option value="ул.Адоратского, 77Б">ул.Адоратского, 77Б</option>
                <option value="ул.Киямдина Ильдарова, 47Ф">ул.Дмитрия Итиса, 69А</option>
                <#elseif moscow??>
                    <option value="ул.Живописная, 2">ул.Живописная, 2</option>
                    <option value="ул.Затонная,105">ул.Затонная,105</option>
                    <option value="Елисеевский пер.,25А">Елисеевский пер.,25А</option>
                    <#elseif petersburg??>
                <option value="Невский проспект,17">Невский проспект,17</option>
                <option value="ул.Пушкина,30">ул.Пушкина,30</option>
                <option value="Литейный проспект, 36">Литейный проспект, 36</option>
            </#if>

        </select>
    </div>
        <input hidden name="order_id" value="${order_id}">
    <button type="submit">Завершить оформление</button>
    </form>
</div>

<style>
    .credit-card-div span {
        padding-top: 10px;
    }

    .credit-card-div img {
        padding-top: 30px;
    }

    .credit-card-div .small-font {
        font-size: 9px;
    }

    .credit-card-div .pad-adjust {
        padding-top: 10px;
    }
</style>

<script>
    $(function () {
        $('#date').daterangepicker();
    });
</script>

</body>
</@header.main>