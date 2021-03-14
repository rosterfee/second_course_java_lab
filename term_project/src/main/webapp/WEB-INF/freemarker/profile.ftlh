<#ftl encoding="utf-8">

<#import "header.ftlh" as header>

<@header.main>
<body class="bg-info">
<div class="mt-5 bg-light container mb-5" rounded>
    <div class="row">
        <div class="col-md-5 mt-5 avatar-zone">
            <div class="avatar-zone-name">
                <h3 class="pl-4" class="avatar-zone-name">${user.firstName} ${user.lastName}</h3>
            </div>
            <#--            <img class="rounded-circle" width="70%" src="${user.avatar}" alt="">-->
            <img  class="user-avatar" width="50%" src="${user.avatar}" alt="">
            <div>
                <form action="/upload_user_image" method="post" enctype="multipart/form-data">
                    <input type="file" name="file">
                    <input type="submit" placeholder="Изменить аватар">
                </form>
            </div>
        </div>
        <div class="col-md-7">
            <form class="mt-3 pb-3" method="post" action="/profile">
                <div class="fields form-group">
                    <label for="name">Имя:</label>
                    <input type="text" id="name" name="first_name" value="${user.firstName}" placeholder="" required>
                    <span class="dot">*</span>
                </div>
                <div class="fields form-group">
                    <label for="surname">Фамилия:</label>
                    <input type="text" id="surname" name="last_name" value="${user.lastName}" placeholder="" required>
                    <span class="dot">*</span>
                </div>
                <div class="fields form-group">
                    <label for="email">E-mail:</label>
                    <input type="text" id="email" name="email" value="${user.email}" placeholder="" required>
                    <span class="dot">*</span>
                </div>
                <div class="fields form-group">
                    <label for="phone_number">Номер телефона:
                    <input type="text" id="phone_number" name="phone" value="${user.phone}" placeholder="" required>
                        <#if badPhoneNumber??>
                            ${badPhoneNumber}
                        </#if>
                    </label>
                    <span class="dot">*</span>
                </div>
                <div class="fields form-group">
                    <label for="login">Логин:</label>
                    <input type="text" id="login" name="login" value="${user.login}" placeholder="" required>
                    <span class="dot">*</span>
                </div>
                <div class="fields form-group">
                    <label for="pass1">Пароль:
                    <input type="password" id="pass1" name="password" value="" required>
                        <#if badPassword??>
                            ${badPassword}
                        </#if>
                    </label>
                    <span class="dot">*</span>
                </div>
<#--                <button type="button" class="btn btn-info" onclick="enableForm()" name="button1" id="enable_btn">Изменить данные профиля</button>-->
                <button type="submit" class="btn btn-info" onclick="disabeleForm()" name="button2" id="disable_btn">Подтвердить данные</button>
            </form>
        </div>
    </div>
    <div class="row mt-5 pb-3">
        <div class="col-md-12">
            <h2 class="text-center">История заказов</h2>
            <div class="catalog-content">
                <div class="catalog-product history-list">
                    <#list orders as order>
                    <div class="card history-list" style="width: 25%">
                        <a href="/car_profile?car_id=${order.car.id}"><img class="card-img-top" src="${order.car.photos[0].image}"></a>
                        <div class="card-body text-center">
                            ${order.car.mark + " " + order.car.model}
                            <div>
                                Дата заказа: ${order.date}
                            </div>
                        </div>
                    </div>
                    <#else><h5>У вас нет завершенных заказов</h5>
                    </#list>
                </div>

            </div>
        </div>
    </div>
</div>

<script src="//code.jquery.com/jquery-1.7.min.js"></script>
<style>
    #main {
        background-color: #f2f2f2;
        padding: 20px;
        -webkit-border-radius: 4px;
        -moz-border-radius: 4px;
        -ms-border-radius: 4px;
        -o-border-radius: 4px;
        border-radius: 4px;
        border-bottom: 4px solid #ddd;
    }

    #real-estates-detail #author img {
        -webkit-border-radius: 100%;
        -moz-border-radius: 100%;
        -ms-border-radius: 100%;
        -o-border-radius: 100%;
        border-radius: 100%;
        border: 5px solid #ecf0f1;
        margin-bottom: 10px;
    }

    #real-estates-detail .sosmed-author i.fa {
        width: 30px;
        height: 30px;
        border: 2px solid #bdc3c7;
        color: #bdc3c7;
        padding-top: 6px;
        margin-top: 10px;
    }

    .user-avatar {
        margin-left: 100px;
        margin-bottom: 20px;
    }

    .avatar-zone-name {
        margin-left: 100px;
    }

    .avatar-zone {
        display: flex;
        flex-direction: column;
        justify-content: center;
    }

    .panel-default .panel-heading {
        background-color: #fff;
    }

    #real-estates-detail .slides li img {
        height: 450px;
    }

    .history-list {
        display: flex;
        flex-wrap: wrap;
        margin-left: 50px;
        margin-top: 30px;
        margin-bottom: 40px;
    }

</style>

</body>
</@header.main>