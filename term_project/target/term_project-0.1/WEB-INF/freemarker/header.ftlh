<#macro main>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>CarSharing</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
            crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
            integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"
            integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s"
            crossorigin="anonymous"></script>
</head>

<body class="bg-info">
<img class="img-fluid" src="../../../../../../../CarSharing/CarSharing/src/main/webapp/pictures/main_picture.jpg" alt=""/>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">CarSharing</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/catalog">Каталог</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/about_us">О нас</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/feedbacks">Отзывы</a>
            </li>
            <#if non_auth??>
            <li class="nav-item">
                <a class="nav-link" href="/sign_in">Войти</a>
            </li>
            </#if>

            <#if auth?? || admin??>
            <li class="nav-item">
                <a class="nav-link" href="/my_order">Мой заказ</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/profile">Профиль</a>
            </li>
            </#if>

            <#if admin??>
            <li class="nav-item">
                <a class="nav-link" href="/add_car">Добавить авто</a>
            </li>
            </#if>

            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">
                    Город
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="/choose_city?city=Казань">Казань</a>
                    <a class="dropdown-item" href="/choose_city?city=Москва">Москва</a>
                    <a class="dropdown-item" href="/choose_city?city=Санкт-Петербург">Санкт-Петербург</a>
                </div>
            </li>
            <#if city??>
                ${city}
            </#if>


        </ul>

        <#if auth?? || admin??>
            <a class="btn btn-danger mr-3" href="/sign_out">Выйти</a>
        </#if>

        <div class="language_zone">
            <a href="?lang=ru">RU</a>
            <a href="?lang=en">EN</a>
        </div>

        <#if catalog??>
        <form class="form-inline my-2 my-lg-0" method="post" action="/catalog">
            <input class="form-control mr-sm-2" type="text" placeholder="Поиск в каталоге..." aria-label="Search"
                   name="search" id="search">
            <button id="btn_search" class="btn btn-outline-success my-2 my-sm-0" type="submit">Поиск</button>
        </form>
        </#if>
    </div>
</nav>

<#nested>

</body>

<style>
    .language_zone a {
        display: flex;
        flex-direction: row;
        margin-right: 10px;
    }
</style>

</html>
</#macro>