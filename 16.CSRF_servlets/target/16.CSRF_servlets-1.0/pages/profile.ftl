<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>profile</title>
</head>
<body>
    <form method="post" action="/profile?action=delete&user_id=${user.id}">
        <input type="hidden" name="_csrf_token" value="${_csrf_token}">
        <button type="submit">delete the user</button>
    </form>
</body>
</html>