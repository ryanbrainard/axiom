<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
	<s:include value="%{getText('app.includes.head')}" />
</head>

<body>
	<s:include value="%{getText('app.includes.body_header')}" />

    <div style="width: 500px; float: left;">
        <p style="word-wrap: break-word;"><strong>Id:</strong><br/><s:text name="oauthContext.id"/></p>
        <p style="word-wrap: break-word;"><strong>Access Token:</strong><br/><s:text name="oauthContext.access_token"/></p>
        <p style="word-wrap: break-word;"><strong>Refresh Token:</strong><br/><s:text name="oauthContext.refresh_token"/></p>
        <p><strong>Issued At:</strong><br/><s:text name="oauthContext.issued_at"/></p>
        <p><strong>Instance URL:</strong><br/><s:text name="oauthContext.instance_url"/></p>

        <hr/>

        <p>
            <strong>Examples Accessing Salesforce as User</strong><br/>
            <ul>
                <li><a target="_blank" href="<s:text name="oauthContext.id"/>?oauth_token=<s:text name="oauthContext.access_token"/>">Identity</a></li>
                <li><a target="_blank" href="http://workbench/login.php?sid=<s:text name="oauthContext.access_token"/>&serverUrlPrefix=<s:text name="oauthContext.instance_url"/>">Workbench</a></li>
            </ul>
        </p>
    </div>

    <div style="float: right;">
        <img src="<s:url value="%{getText('img.oauth.2_0.webapp.flow5')}"/>" border="0"/>
    </div>

    <div style="clear: both;">
    </div>

	<s:include value="%{getText('app.includes.body_footer')}" />
</body>

</html>