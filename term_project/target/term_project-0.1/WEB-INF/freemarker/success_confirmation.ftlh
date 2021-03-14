<#ftl encoding="utf-8">

<#import "header.ftlh" as header>

<style>
    .to_main_button {
        border-radius: 10px;
        padding: 5px 10px;
        background-color: #3BA4C7;
        font-weight: bold;
    }
</style>

<@header.main>
    <body class="bg-info">
    <h1>
        Ваш email успешно подтвержден.
        <a href="/sign_in" class="to_main_button">Войти в аккаунт</a>
    </h1>
</@header.main>
