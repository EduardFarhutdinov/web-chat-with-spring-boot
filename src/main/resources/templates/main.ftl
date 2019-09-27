<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
    <div>This is home Page in my application!</div>

    <div>
        <form method="post">
            <input type="text" name="messageText" placeholder="Введите сообщение"/>
            <input type="text" name="tag" placeholder="Тэг"/>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button type="submit">Добавить</button>
        </form>
    </div>


    <div>
     <@l.logout/>
    </div>


    <div>Список сообщений</div>
    <form method="get" action="/main">
        <input type="text" name="tag" value="${tag}">
        <button type="submit">Найти</button>
    </form>


   <#list allMessages as messages>
        <div>
            <b>${messages.id}</b>
            <span>${messages.text}</span>
            <i>${messages.tag}</i>
            <strong>by  ${messages.authorName}</strong>
        </div>
    <#else>
        No message
    </#list>

</@c.page>
