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

        function buildAccessUrl() {
            var form = document.forms.OAuth2RequestAccessToken_action;
            var host = trim('<s:text name="oauthContext.host"/>');
            var client_id = trim('<s:text name="oauthContext.client_id"/>');
            var redirect_uri = trim('<s:text name="oauthContext.redirect_uri"/>');
            var client_secret = trim(form.OAuth2RequestAccessToken_action_oauthContext_client_secret.value);
            var code = trim(form.OAuth2RequestAccessToken_action_oauthContext_code.value);

            var hostString = "https://" +
                             host + "/services/oauth2/token?&grant_type=authorization_code" +
                             "&client_id=" + client_id +
                             "&code=" + code +
                             "&client_secret=" + client_secret +
                             "&redirect_uri=" + escape(redirect_uri);

            form.OAuth2RequestAccessToken_action_oauthContext_tokenRequestUrl.value = hostString;
        }
    </script>
</head>

<body onload="buildAccessUrl();">
    <s:include value="%{getText('app.includes.body_header')}" />

    <div style="float: left; width:400;">
        <s:actionerror cssStyle="color: red;"/>
        
        <p>
            <strong><s:text name="label.oauth.2_0.host"/></strong><br/>
            <s:text name="oauthContext.host"/>
        </p>
        <p style="word-wrap: break-word;">
            <strong><s:text name="label.oauth.2_0.consumerKey"/></strong><br/>
            <s:text name="oauthContext.client_id"/>
        </p>
        <p>
            <strong><s:text name="label.oauth.2_0.redirectUri"/></strong><br/>
            <s:text name="oauthContext.redirect_uri"/>
        </p>
         <br/>
         <s:form method="POST" action="OAuth2RequestAccessToken.action">
            <s:textfield key="oauthContext.code" label="%{getText('label.oauth.2_0.authCode')}" labelposition="top" size="60" onkeyup="buildAccessUrl();" onchange="buildAccessUrl();" />
            <s:textfield key="oauthContext.client_secret" label="%{getText('label.oauth.2_0.consumerSecret')}" labelposition="top" size="60" onkeyup="buildAccessUrl();" onchange="buildAccessUrl();" />

            <s:textarea key="oauthContext.tokenRequestUrl" label="Access Token URL" cols="63" rows="8" labelposition="top"/>

            <s:submit value="%{getText('label.oauth.2_0.requestAccessToken')}"/>
        </s:form>
    </div>


    <div style="float: right;">
        <img src="<s:url value="%{getText('img.oauth.2_0.webapp.flow3')}"/>" border="0"/>
    </div>

    <div style="clear: both;">
    </div>

    <s:include value="%{getText('app.includes.body_footer')}" />
</body>

</html>