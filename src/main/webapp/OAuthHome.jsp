<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
	<s:include value="%{getText('app.includes.head')}" />
</head>

<body>
	<s:include value="%{getText('app.includes.body_header')}" />

    <table width="100%">
        <tr>
            <th><s:text name="label.oauth.1_0a"/></th>
            <th><s:text name="label.oauth.2_0"/></th>
        </tr>
        <tr>
            <td><img src="<s:url value="%{getText('img.oauth.1_0a.flow')}"/>" border="0"/></td>
            <td><img src="<s:url value="%{getText('img.oauth.2_0.webapp.flow')}"/>" border="0" align="right"/></td>
        </tr>
    </table>

	<s:include value="%{getText('app.includes.body_footer')}" />
</body>

</html>