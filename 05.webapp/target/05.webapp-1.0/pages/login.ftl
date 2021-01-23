<#ftl encoding="utf-8">

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Войти</title>
    <link rel="stylesheet" href="../styles/sign_in.css">
</head>
<body>
<div class="sign_in_content">
    <div class="sign_in_window">
        <form class="sign_in_form" action="/login" method="post">
            <div class="login_zone">
                <label>
                    Логин
                    <input type="text" required name="login">
                </label>
            </div>
            <div class="password_zone">
                <label>
                    Пароль
                    <input type="text" required name="password">
                </label>
            </div>
        <button  class="sign_in_button" type="submit">Войти</button>
        </form>
    </div>
</div>
</body>
</html>