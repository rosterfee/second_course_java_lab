<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>sign_in</title>
</head>
<body>
    <form method="post" action="/sign_in">
        <label>
            <input type="text" name="login" placeholder="login">
        </label>
        <label>
            <input type="password" name="password" placeholder="password">
        </label>
        <input type="hidden" name="_csrf_token" value="${_csrf_token}">
        <button type="submit">Sign in</button>
    </form>

    <#if status??>
        <h3>${status}</h3>
    </#if>
</body>
</html>