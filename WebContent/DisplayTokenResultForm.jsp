<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
	<s:include value="%{getText('app.includes.head')}" />
	
	<style>
		#tokenInfo th, #tokenInfo td{
			padding-bottom: 10pt;
		}
		
		#tokenInfo th{
			font-weight: bold;
			text-align: right;
			padding-right: 10px;
		}
	</style>
	
	<SCRIPT type="text/javascript">
		window.name = "tokenMain";
	</SCRIPT>
</head>

<body>
	<s:include value="%{getText('app.includes.body_header')}" />
		
	<p>
		Successfully generated a token and stored in the Axiom Token Store.  
		Click the Login URL to send these login credentials to Salesforce. 
		If the Salesforce organization and user have been properly 
		configured for Delegated Authentication Single Sign-On with this Axiom service, the 
		token will be returned to Axiom for authentication against the Token Store.
	</p>
	
	<form action="${baseLoginUrl}" method="POST">
		<table id="tokenInfo">
			<tr><th>&nbsp;</th><td><strong style="font-size: 12pt;">POST Login Request</strong></td></tr>
			<tr><th></th><td><strong>${baseLoginUrl}</strong></td></tr>
			<tr><th><s:text name="label.delauth.username"/></th><td><input type="text" name="un" value="${username}" size="40"/></td></tr>
			<tr><th><s:text name="label.delauth.tokenPassword"/></th><td><input type="text" name="pw" value="${token}" size="40"/></td></tr>
			
			<tr><th></th><td><em>Custom URL Options</em></td></tr>
			<tr><th><s:text name="label.ssoStartPage"/></th><td><input type="text" name="ssoStartPage" value="${ssoStartPage}" size="40"/></td></tr>
			<tr><th><s:text name="label.startURL"/></th><td><input type="text" name="startURL"  value="${startURL}" size="40"/></td></tr>
			<tr><th><s:text name="label.logoutURL"/></th><td><input type="text" name="logoutURL" size="40"/></td></tr>
			
			<s:if test="portalLogin">
				<tr><th></th><td><em>Portal Options</em></td></tr>
				<tr><th><s:text name="label.orgId"/></th><td><input type="text" name="orgId" size="40"/></td></tr>
				<tr><th><s:text name="label.portalId"/></th><td><input type="text" name="portalId" size="40"/></td></tr>
				<tr><th><s:text name="label.delauth.loginType"/></th><td><input type="text" name="loginType" value="2" size="40"/></td></tr>
			</s:if>
			
			<tr><th></th><td><input type="submit" value="<s:text name="label.login"/>"/></td></tr>
		</table>
	</form>
	
	<p>
		To view all token entries, see the <a href="DisplayTokenStore.action" target="tokenViewer">Token Store Viewer</a>. Note, in a production environment, the Token Store would <em>never</em> be exposed, but it is displayed here for demonstration purposes.
	</p>
		
	<s:include value="%{getText('app.includes.body_footer')}" />
</body>

</html>