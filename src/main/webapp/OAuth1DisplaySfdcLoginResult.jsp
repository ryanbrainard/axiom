<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
	<s:include value="%{getText('app.includes.head')}" />
</head>

<body >
	<s:include value="%{getText('app.includes.body_header')}" />

    <div style="width: 500px; float: left;">
        <p style="word-wrap: break-word;"><strong><s:text name="label.oauth.1_0a.sfdcSessionId"/></strong><br/><s:text name="oauthContext.sfdcSessionId"/></p>
        <p><strong><s:text name="label.oauth.1_0a.sfdcServerUrl"/></strong><br/><s:text name="oauthContext.sfdcServerUrl"/></p>

        <hr/>

        <!--
        <p>
            <strong>Examples Accessing Salesforce as User</strong><br/>
            <ul>
                <li><a target="_blank" href="http://workbench/login.php?sid=<s:text name="oauthContext.sfdcSessionId"/>&serverUrlPrefix=<s:text name="oauthContext.sfdcServerUrl"/>">Workbench</a></li>
            </ul>
        </p>
        -->
    </div>

    <div style="float: right;">
        <img src="<s:url value="%{getText('img.oauth.1_0a.flow6')}"/>" border="0"/>
    </div>

    <div style="clear: both;">
    </div>

	<s:include value="%{getText('app.includes.body_footer')}" />
</body>

</html>