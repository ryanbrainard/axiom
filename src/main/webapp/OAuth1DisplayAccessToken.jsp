<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
	<s:include value="%{getText('app.includes.head')}" />
</head>

<body >
	<s:include value="%{getText('app.includes.body_header')}" />

    <div style="float: left;">
        <s:actionerror cssStyle="color: red;"/>
        <s:form method="POST" action="OAuth1RequestSfdcSessionId.action">
            <s:textarea key="oauthContext.oauth_token" label="%{getText('label.oauth.1_0a.oauth_access_token')}" labelposition="top"  cols="53" rows="3" />
            <s:textarea key="oauthContext.oauth_token_secret" label="%{getText('label.oauth.1_0a.oauth_token_secret')}" labelposition="top"  cols="53" rows="3" cssStyle="margin-bottom: 2em;" />

            <s:textfield key="oauthContext.sfdcLoginUrl" label="%{getText('label.oauth.1_0a.sfdcLoginUrl')}" labelposition="top" size="50"/>


            <s:submit value="%{getText('label.oauth.1_0a.requestSfdcSessonId')}"/>
        </s:form>
	</div>

    <div style="float: right;">
        <img src="<s:url value="%{getText('img.oauth.1_0a.flow6')}"/>" border="0"/>
    </div>

    <div style="clear: both;">
    </div>

	<s:include value="%{getText('app.includes.body_footer')}" />
</body>

</html>