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
	        var host = trim(form.OAuth2RequestAuthCode_action_oauthContext_host.value);
	        var client_id = trim(form.OAuth2RequestAuthCode_action_oauthContext_client_id.value);
	        var redirect_uri = trim(form.OAuth2RequestAuthCode_action_oauthContext_redirect_uri.value);

	        var authRequestUrl = "https://" + host +
                                 "/services/oauth2/authorize?response_type=code&display=popup&client_id=" + client_id +
                                 "&redirect_uri=" + escape(redirect_uri);

            form.OAuth2RequestAuthCode_action_oauthContext_authRequestUrl.value = authRequestUrl;
	    }
	</script>
</head>

<body onload="buildAuthorizationUrl();">
	<s:include value="%{getText('app.includes.body_header')}" />

    <div style="float: left;">
        <s:actionerror cssStyle="color: red;"/>
        <s:form method="POST" action="OAuth2RequestAuthCode.action">
            <s:textfield key="oauthContext.host" label="%{getText('label.oauth.2_0.host')}" labelposition="top" size="60" onkeyup="buildAuthorizationUrl();" onchange="buildAuthorizationUrl();"/>
            <s:textfield key="oauthContext.client_id" label="%{getText('label.oauth.2_0.consumerKey')}" labelposition="top" size="60" onkeyup="buildAuthorizationUrl();" onchange="buildAuthorizationUrl();"/>
            <s:textfield key="oauthContext.redirect_uri" label="%{getText('label.oauth.2_0.redirectUri')}" labelposition="top" size="60" onkeyup="buildAuthorizationUrl();" onchange="buildAuthorizationUrl();" cssStyle="margin-bottom: 2em;"/>

            <s:textarea key="oauthContext.authRequestUrl" label="%{getText('label.oauth.2_0.authUrl')}" cols="63" rows="6" labelposition="top"/>

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