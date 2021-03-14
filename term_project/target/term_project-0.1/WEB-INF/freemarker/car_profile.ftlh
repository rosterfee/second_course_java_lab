<#ftl encoding="utf-8">

<#import "header.ftlh" as header>

<@header.main>
<body class="bg-info">
<div class="row ml-1 mt-5 mr-1">
    <div class="col-md-6">
        <div id="carousel" class="carousel slide" data-ride="carousel">
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <img class="img-fluid" src="${car.photos[0].image}" alt="">
                </div>
                <div class="carousel-item">
                    <img class="img-fluid" src="${car.photos[1].image}" alt="">
                </div>
                <div class="carousel-item">
                    <img class="img-fluid" src="${car.photos[2].image}" alt="">
                </div>
            </div>
            <a class="carousel-control-prev" href="#carousel" role="button" data-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="sr-only">Предыдущий</span>
            </a>
            <a class="carousel-control-next" href="#carousel" role="button" data-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="sr-only">Следующий</span>
            </a>
        </div>
    </div>
    <div class="col-md-6 mt-5">
        <p class="text-center  bg-light rounded">
            <big>${car.description}</big>
        </p>
        <ul class="text-left bg-light pl-2 rounded list-unstyled">
            <li><big>Краткая справка:</big></li>
            <ul>
                <li>Мощность : ${car.powers}.</li>
                <li>Объем двигателя : ${car.engine} л.</li>
                <li>Тип коробки передач : ${car.transmission}</li>
                <li>Максимальная скорость : ${car.maxSpeed} км/ч</li>
                <li>Разгон до 100 : ${car.racing} сек</li>
            </ul>
        </ul>
        <h4>Цена за 4 часа аренды: ${car.price}</h4>

        <form action="/car_profile" method="post">
            <input hidden name="car_id" value="${car.id}">
            <#if userHasActiveOrder??>
                <strong>${userHasActiveOrder}</strong>
            </#if>
            <button type="submit" class="btn btn-outline-light btn-lg btn-block">Заказать</button>
        </form>

    </div>
</div>
<div class="row ml-1 mr-1">
    <div class="col-md-6">
        <h2 class="bg-light rounded text-center">${car.mark + " " + car.model}</h2>
    </div>
</div>
</body>
</@header.main>