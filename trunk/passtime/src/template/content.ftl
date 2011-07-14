<html>
<head>
    <title>passtime.me</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="/Users/user/IdeaProjects/passtime/web/resources/css/common.css" rel="stylesheet"/>
</head>
<body>
    <#list objects as obj>
        <div class="w_div">
            ${obj.content}
            <#if obj.imageUrl != "">
               <div class="m_div">
                   ${obj.imageUrl}
                </div>
            </#if>
        </div>
    </#list>
</body>
</html>