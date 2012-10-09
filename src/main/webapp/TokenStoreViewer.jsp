<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
    <s:include value="%{getText('app.includes.head')}" />
</head>

<body>
    <s:include value="%{getText('app.includes.body_header')}" />

    <strong>Token Store Entries</strong>

    <p>
    <s:if test="%{TokenStoreEntries.size() > 0}">
        <ul>
            <s:iterator value="TokenStoreEntries" var="e">
                <li>${e}</li>
            </s:iterator>
        </ul>
        <s:form action="ClearTokenStore.action" method="POST">
            <s:submit value="Clear Token Store"/>
        </s:form>
    </s:if>
    <s:else>
        <em>There are currently no entries.</em>

        <s:form action="GenerateToken.action" method="POST" target="tokenMain">
            <s:submit value="Generate a New Token"/>
        </s:form>
    </s:else>
    </p>


    <s:include value="%{getText('app.includes.body_footer')}" />
</body>

</html>