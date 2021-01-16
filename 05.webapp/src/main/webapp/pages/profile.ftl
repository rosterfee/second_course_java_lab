<#ftl encoding="utf-8">

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Войти</title>
    <link rel="stylesheet" href="../styles/profile.css">
</head>
<body>
    <h4>Информация о пользователе:</h4>
    <table class="profile-table">
        <tr>
            <th>Имя</th>
            <th>Фамилия</th>
            <th>Возраст</th>
        </tr>
        <tr>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.age}</td>
        </tr>
    </table>
</body>
</html>