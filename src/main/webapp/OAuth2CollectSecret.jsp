<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
	<s:include value="%{getText('app.includes.head')}" />
</head>

<body>
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
            <s:textfield key="authCode" label="%{getText('label.oauth.2_0.authCode')}" labelposition="top" size="60" readonly="true" />
            <s:textfield key="consumerSecret" label="%{getText('label.oauth.2_0.consumerSecret')}" labelposition="top" size="60"/>

             <s:textarea key="authUrl" label="%{getText('label.oauth.2_0.authUrl')}" cols="63" rows="5" labelposition="top"/>

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