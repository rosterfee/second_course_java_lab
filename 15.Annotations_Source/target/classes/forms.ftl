<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>forms</title>
</head>
<body>
    <#list forms as form>
        <form action="${form.action}" method="${form.method}">
            <#list form.inputs as input>
                <label>
                    <input type="${input.type}" name="${input.name}" placeholder="${input.placeholder}">
                </label>
            </#list>
            <button type="submit">Отправить</button>
        </form>
    </#list>
</body>
</html>