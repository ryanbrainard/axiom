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

        function buildUrls() {
            var form = document.forms.OAuth1RequestRequestToken_action;
            var host = trim(form.OAuth1RequestRequestToken_action_oauthContext_host.value);
            var oauth_consumer_key = trim(form.OAuth1RequestRequestToken_action_oauthContext_oauth_consumer_key.value);
            var oauth_consumer_secret = trim(form.OAuth1RequestRequestToken_action_oauthContext_oauth_consumer_secret.value);
            var oauth_callback = trim(form.OAuth1RequestRequestToken_action_oauthContext_oauth_callback.value);

            var requestTokenUrl = "https://" + host + "/_nc_external/system/security/oauth/RequestTokenHandler";
            var accessTokenUrl = "https://" + host + "/_nc_external/system/security/oauth/AccessTokenHandler";
            var authUrl = "https://" + host + "/setup/secur/RemoteAccessAuthorizationPage.apexp?oauth_consumer_key=" + escape(oauth_consumer_key);

            form.OAuth1RequestRequestToken_action_oauthContext_requestTokenUrl.value = requestTokenUrl;
            form.OAuth1RequestRequestToken_action_oauthContext_accessTokenUrl.value = accessTokenUrl;
            form.OAuth1RequestRequestToken_action_oauthContext_authUrl.value = authUrl;
        }
    </script>
</head>

<body onload="buildUrls();">
    <s:include value="%{getText('app.includes.body_header')}" />

    <div style="float: left;">
        <s:actionerror cssStyle="color: red;"/>
        <s:form method="POST" action="OAuth1RequestRequestToken.action">
            <s:textfield key="oauthContext.host" label="%{getText('label.oauth.1_0a.host')}" labelposition="top" size="50" onkeyup="buildUrls();" onchange="buildUrls();"/>
            <s:textfield key="oauthContext.oauth_consumer_key" label="%{getText('label.oauth.1_0a.oauth_consumer_key')}" labelposition="top" size="50" onkeyup="buildUrls();" onchange="buildUrls();"/>
            <s:textfield key="oauthContext.oauth_consumer_secret" label="%{getText('label.oauth.1_0a.oauth_consumer_secret')}" labelposition="top" size="50" onkeyup="buildUrls();" onchange="buildUrls();"/>
            <s:textfield key="oauthContext.oauth_callback" label="%{getText('label.oauth.1_0a.oauth_callback')}" labelposition="top" size="50" onkeyup="buildUrls();" onchange="buildUrls();" cssStyle="margin-bottom: 2em;"/>

            <s:textarea key="oauthContext.requestTokenUrl" label="%{getText('label.oauth.1_0a.requestTokenUrl')}" labelposition="top"  cols="53" rows="2" onkeyup="buildUrls();" onchange="buildUrls();"/>
            <s:textarea key="oauthContext.accessTokenUrl" label="%{getText('label.oauth.1_0a.accessTokenUrl')}" labelposition="top"  cols="53" rows="2" onkeyup="buildUrls();" onchange="buildUrls();"/>
            <s:textarea key="oauthContext.authUrl" label="%{getText('label.oauth.1_0a.authUrl')}" labelposition="top"  cols="53" rows="4" onkeyup="buildUrls();" onchange="buildUrls();"/>

            <s:submit value="%{getText('label.oauth.1_0a.requestRequestToken')}"/>
        </s:form>
    </div>

    <div style="float: right;">
        <img src="<s:url value="%{getText('img.oauth.1_0a.flow1')}"/>" border="0"/>
    </div>

    <div style="clear: both;">
    </div>

    <s:include value="%{getText('app.includes.body_footer')}" />
</body>

</html>