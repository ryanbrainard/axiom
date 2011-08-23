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
	        var host = trim('<s:text name="host"/>');
	        var consumerKey = trim('<s:text name="consumerKey"/>');
	        var redirectUri = trim('<s:text name="redirectUri"/>');
	        var consumerSecret = trim(form.OAuth2RequestAccessToken_action_consumerSecret.value);
	        var authCode = trim(form.OAuth2RequestAccessToken_action_authCode.value);

            var hostString = "https://" +
                             host + "/services/oauth2/token?&grant_type=authorization_code" +
                             "&client_id=" + consumerKey +
                             "&code=" + authCode +
                             "&client_secret=" + consumerSecret +
                             "&redirect_uri=" + escape(redirectUri);

            form.OAuth2RequestAccessToken_action_accessTokenUrl.value = hostString;
	    }
	</script>
</head>

<body onload="buildAccessUrl();">
	<s:include value="%{getText('app.includes.body_header')}" />

    <div style="float: left; width:400;">
        <p>
            <strong><s:text name="label.oauth.2_0.host"/></strong><br/>
            <s:text name="host"/>
        </p>
        <p style="word-wrap: break-word;">
            <strong><s:text name="label.oauth.2_0.consumerKey"/></strong><br/>
            <s:text name="consumerKey"/>
        </p>
        <p>
            <strong><s:text name="label.oauth.2_0.redirectUri"/></strong><br/>
            <s:text name="redirectUri"/>
        </p>
         <br/>
         <s:form method="POST" action="OAuth2RequestAccessToken.action">
            <s:textfield key="authCode" label="%{getText('label.oauth.2_0.authCode')}" labelposition="top" size="60" onkeyup="buildAccessUrl();" onchange="buildAccessUrl();" />
            <s:textfield key="consumerSecret" label="%{getText('label.oauth.2_0.consumerSecret')}" labelposition="top" size="60" onkeyup="buildAccessUrl();" onchange="buildAccessUrl();" />

            <s:textarea key="accessTokenUrl" label="Access Token URL" cols="63" rows="8" labelposition="top"/>

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