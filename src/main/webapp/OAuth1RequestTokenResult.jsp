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
        <s:form method="POST" action="OAuth1RedirectToAuthUrl.action">
            <s:textarea key="oauthContext.authUrlWithToken" label="%{getText('label.oauth.1_0a.authUrlWithToken')}" labelposition="top"  cols="53" rows="6" />

            <s:submit value="%{getText('label.oauth.1_0a.redirectToAuthUrlWithToken')}"/>
        </s:form>
	</div>

    <div style="float: right;">
        <img src="<s:url value="%{getText('img.oauth.1_0a.flow3')}"/>" border="0"/>
    </div>

    <div style="clear: both;">
    </div>

	<s:include value="%{getText('app.includes.body_footer')}" />
</body>

</html>