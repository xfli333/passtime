<html>
<head>
    <title>passtime.me</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <style type="text/css">
        .m_div {
            margin: 15px 0 0 0;
            padding: 10px;
            border: 2px solid #aaa396;
            display: inline-block;
            -webkit-border-radius: 15px;
            -webkit-box-shadow: 2px 2px 10px #ccc;
            background: -webkit-gradient(linear, 0 20%, 0 100%, from(#f3f3f3), to(#d8d6d3));
        }

        .w_div {
            margin: 15px 0 0 0;
            padding: 10px;
            border: 2px solid #aaa396;
            -webkit-border-radius: 15px;
            -webkit-box-shadow: 2px 2px 10px #ccc;
            /*background: -webkit-gradient(linear, 0 20%, 0 100%, from(#f3f3f3), to(#d8d6d3));*/
        }
    </style>
</head>
<body>
<#list objects as obj>
<div class="w_div">
    <div class="m_div"></div>
    <br>
    <br>
${obj.content}
    <#if obj.imageUrl != "">
        <div class="m_div">
        ${obj.imageUrl}
        </div>
    </#if>

</div>
</#list>

<br/>

<div class="w_div">
<#list 0..19 as i>
    <a href="content${i}.html">${i}</a>
</#list>
</div>

<br/>
<br/>
<br/>
</body>
</html>