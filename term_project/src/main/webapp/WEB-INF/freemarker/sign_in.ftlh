<#ftl encoding="utf-8">

<#import "header.ftlh" as header>

<@header.main>
<body class="bg-info">
<div class="container bg center">
    <div class="row main-form" style="margin: 30px 60px">
        <div class="col-md-4">
        </div>
        <div class="col-md-4 mt-5 bg-light rounded">
            <form class="needs-validation mt-3 mb-3" id="signInForm" novalidate method="POST" action="/sign_in">
                <div class="form-group">
                    <label for="email">Логин</label>
                    <input class="form-control" id="email" name="email" placeholder="Email" required>
                    <small id="emailHelp" class="form-text text-muted">Введите логин</small>
                </div>
                <div class="form-group">
                    <label for="password">Пароль</label>
                    <input type="password" class="form-control" name="password" id="password"
                           placeholder="Пароль" required>
                    <small id="emailHelp" class="form-text text-muted">Введите пароль</small>
                </div>
                <button type="submit" class="btn btn-primary sign-in-sign-in-button">Войти</button>
            </form>
        </div>
    </div>
</div>
</body>
</@header.main>

<style>
    .sign-in-message {
        color: red;
        font-weight: bold;
        display: flex;
        justify-content: center;
        margin-bottom: 10px;
        font-size: 14px;
    }

    .sign-in-sign-in-button {
        width: 35%;
    }

    .sign-in-registration-button {
        width: 60%;
    }

</style>