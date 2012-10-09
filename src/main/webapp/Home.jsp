<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
    <s:include value="%{getText('app.includes.head')}" />
</head>

<body>
    <s:include value="%{getText('app.includes.body_header')}"/>

    <ul class="mainmenu">
        <li><s:a href="SamlIdpHome.action"><s:text name="label.saml.idp"/></s:a></li>
        <li><s:a href="GenerateToken.action"><s:text name="label.delauth.token"/></s:a></li>
        <li><s:a href="SelfAuthenticate.action"><s:text name="label.delauth.self"/></s:a></li>
        <li><s:a href="OAuthHome.action"><s:text name="label.oauth"/></s:a></li>
    </ul>

<s:include value="%{getText('app.includes.body_footer')}" />
</body>

</html>