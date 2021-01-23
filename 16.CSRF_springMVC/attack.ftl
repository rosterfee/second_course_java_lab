<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>profile</title>
</head>
<body>
<form method="post" action="/profile/delete">
    <input type="hidden" name="_csrf_token" value="${_csrf_token}">
    <input type="hidden" name="user_id" value="${user_id}">
    <button type="submit">delete the user</button>
</form>
${_csrf_token}
</body>
</html>