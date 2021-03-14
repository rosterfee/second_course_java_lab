<#ftl encoding="utf-8"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Спасибо за регистрацию!</title>
    <style>
        .company_name {
            font-weight: bold;
            color: blueviolet;
        }
    </style>
</head>
<body>
    <div class="content">

    <img src="https://meduza.io/image/attachments/images/003/728/464/mobile/2fSkKeX5GcgHurd2zAl6ug.png" alt="">
        Недавно вы зарегистрировались на сайте <span class="company_name">CarSharing.ru</span>.
        Для предоставления Вам наших услуг, мы должны удостовериться, что адрес электронной почты,
        которую вы указали при регистрации, действительно принадлежит вам.
        Для подтверждения адреса электронной почты перейти по ссылке:

        <a href="${server_url}/sign_up/confirm/${confirm_code}">
            перейти
        </a>
    </div>
</body>
</html>

<style>
    .content {
        display: flex;
        flex-direction: column;
        justify-content: center;
        margin-bottom: 20px;
    }
</style>