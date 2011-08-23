<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
	<s:include value="%{getText('app.includes.head')}" />
</head>

<body>
	<s:include value="%{getText('app.includes.body_header')}" />
        <img src="<s:url value="%{getText('img.oauth.1_0a.flow')}"/>" border="0"/>
	<s:include value="%{getText('app.includes.body_footer')}" />
</body>

</html>