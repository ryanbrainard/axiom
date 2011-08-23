<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
	<s:include value="%{getText('app.includes.head')}" />

	<script>
	    function trim(str) {
	        if (str === undefined || str === null) {
	            return "";
	        }

            return str.replace(/^\s+|\s+$/g,"");
        }

	    function buildAuthorizationUrl() {
	        var form = document.forms.OAuth2RequestAuthCode_action;
	        var host = trim(form.OAuth2RequestAuthCode_action_host.value);
	        var consumerKey = trim(form.OAuth2RequestAuthCode_action_consumerKey.value);
	        var redirectUri = trim(form.OAuth2RequestAuthCode_action_redirectUri.value);

	        var authUrl = "https://" + host +
                           "/services/oauth2/authorize?response_type=code&display=popup&client_id=" + consumerKey +
                           "&redirect_uri=" + escape(redirectUri);

            form.OAuth2RequestAuthCode_action_authUrl.value = authUrl;
	    }
	</script>
</head>

<body onload="buildAuthorizationUrl();">
	<s:include value="%{getText('app.includes.body_header')}" />

    <div style="float: left;">
        <s:form method="POST" action="OAuth2RequestAuthCode.action">
            <s:textfield key="host" label="%{getText('label.oauth.2_0.host')}" labelposition="top" size="60" onkeyup="buildAuthorizationUrl();" onchange="buildAuthorizationUrl();"/>
            <s:textfield key="consumerKey" label="%{getText('label.oauth.2_0.consumerKey')}" labelposition="top" size="60" onkeyup="buildAuthorizationUrl();" onchange="buildAuthorizationUrl();"/>
            <s:textfield key="redirectUri" label="%{getText('label.oauth.2_0.redirectUri')}" labelposition="top" size="60" onkeyup="buildAuthorizationUrl();" onchange="buildAuthorizationUrl();" cssStyle="margin-bottom: 2em;"/>

            <s:textarea key="authUrl" label="%{getText('label.oauth.2_0.authUrl')}" cols="63" rows="5" labelposition="top"/>

            <s:submit value="%{getText('label.oauth.2_0.requestAuthCode')}"/>
        </s:form>
	</div>

    <div style="float: right;">
        <img src="<s:url value="%{getText('img.oauth.2_0.webapp.flow1')}"/>" border="0"/>
    </div>

    <div style="clear: both;">
    </div>

	<s:include value="%{getText('app.includes.body_footer')}" />
</body>

</html>