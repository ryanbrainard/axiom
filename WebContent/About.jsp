<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
	<s:include value="%{getText('app.includes.head')}" />
</head>

<body>
	<s:include value="%{getText('app.includes.body_header')}" />
		
		<p>
		<strong><s:text name="app.name"/> v<s:text name="version"/></strong><br/>
		Developed by Ryan Brainard
		</p>
		
		<p class="fineprint">
		THIS APPLICATION IS STILL IN ACTIVE DEVELOPMENT AND HAS NOT UNDERGONE COMPLETE QUALITY ASSURANCE TESTING. DO NOT USE WITH PRODUCTION ORGANIZATIONS. THIS APPLICATION IS PROVIDED 'AS IS' AND THE USER ASSUMES ALL RISKS ASSOCIATED WITH ITS USE. 
		</p>
		
		
	<s:include value="%{getText('app.includes.body_footer')}" />
</body>

</html>