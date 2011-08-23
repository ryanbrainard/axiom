<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
	<s:include value="%{getText('app.includes.head')}" />
</head>

<body>
	<s:include value="%{getText('app.includes.body_header')}" />

    <div style="float: left;">
        <s:form method="POST" action="OAuth2RequestAuthCode.action">
            <s:textfield key="host" label="%{getText('label.oauth.2_0.host')}" labelposition="top" size="60"/>
            <s:textfield key="consumerKey" label="%{getText('label.oauth.2_0.consumerKey')}" labelposition="top" size="60"/>
            <s:textfield key="redirectUri" label="%{getText('label.oauth.2_0.redirectUri')}" labelposition="top" size="60"/>

            <s:submit value="%{getText('label.oauth.2_0.requestAuthCode')}"/>
        </s:form>
	</div>


    <div style="float: right;">
        <img src="<s:url value="%{getText('img.oauth.2_0.webapp.flow')}"/>" border="0"/>
    </div>

    <div style="clear: both;">
    </div>

	<s:include value="%{getText('app.includes.body_footer')}" />
</body>

</html>