<#ftl encoding="utf-8">

<#import "header.ftlh" as header>
<#import "spring.ftl" as spring>

<@header.main>
<body>
    <h2><@spring.message "success_sign_up_page.message"/></h2>
    <a href="/main"><@spring.message "success_sign_up_page.go_to_main"/></a>
</body>
</@header.main>