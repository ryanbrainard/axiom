<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
    <s:include value="%{getText('app.includes.head')}" />
    <style>
        a {
            text-decoration: none;
        }
    </style>
</head>

<body>
    <s:include value="%{getText('app.includes.body_header')}" />

    <table width="100%">
        <tr>
            <th><s:a href="OAuth1FlowTester.action"><s:text name="label.oauth.1_0a"/></s:a></th>
            <th><s:a href="OAuth2WebFlowTester.action"><s:text name="label.oauth.2_0"/></s:a></th>
        </tr>
        <tr>
            <td>
                <s:a href="OAuth1FlowTester.action">
                    <img src="<s:url value="%{getText('img.oauth.1_0a.flow')}"/>" border="0"/>
                </s:a>
             </td>
            <td>
                <s:a href="OAuth2WebFlowTester.action">
                    <img src="<s:url value="%{getText('img.oauth.2_0.webapp.flow')}"/>" border="0" align="right"/>
                </s:a>
            </td>
        </tr>
    </table>

    <s:include value="%{getText('app.includes.body_footer')}" />
</body>

</html>