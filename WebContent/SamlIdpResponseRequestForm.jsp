<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
	<s:include value="%{getText('app.includes.head')}" />

	<script type="text/javascript">
		function evalAttribInputsVisibility(){
			if(document.getElementById('GenerateSamlResponse_action_idpConfig_samlUserIdLocationATTRIBUTE').checked){
				document.getElementById('GenerateSamlResponse_action_idpConfig_attributeName').disabled=false;
				document.getElementById('GenerateSamlResponse_action_idpConfig_attributeUri').disabled=false;
			} else {
				document.getElementById('GenerateSamlResponse_action_idpConfig_attributeName').disabled=true;
				document.getElementById('GenerateSamlResponse_action_idpConfig_attributeUri').disabled=true;
			}
		}
	</script>
</head>

<body onload="evalAttribInputsVisibility();">
	<s:include value="%{getText('app.includes.body_header')}" />

	<s:form method="POST" action="GenerateSamlResponse.action">
		<s:select label="%{getText('label.saml.idp.samlVersion')}" key="idpConfig.samlVersion" list="samlVersions"/>
		
		<s:textfield label="%{getText('label.saml.idp.userId')}" key="idpConfig.userId" size="60"/>
		
		<s:radio label="%{getText('label.saml.idp.samlUserIdLocation')}" key="idpConfig.samlUserIdLocation" list="samlUserIdLocations" value="%{getText('default.saml.idp.samlUserIdLocation')}" onclick="evalAttribInputsVisibility();"/>
	
	    <s:textfield label="%{getText('label.saml.idp.attributeName')}" key="idpConfig.attributeName" size="60"/>
	    <s:textfield label="%{getText('label.saml.idp.attributeUri')}" key="idpConfig.attributeUri" size="60"/>

		<s:textfield label="%{getText('label.saml.idp.issuer')}" key="idpConfig.issuer" size="60"/>
	
		<s:textfield label="%{getText('label.saml.idp.recipient')}" key="idpConfig.recipient" size="60"/>
		
		<s:textfield label="%{getText('label.ssoStartPage')}" key="idpConfig.ssoStartPage" size="60"/>

		<s:textfield label="%{getText('label.saml.idp.RelayStateOrStartURL')}" key="idpConfig.startURL" size="60"/>
		
		<s:textfield label="%{getText('label.logoutURL')}" key="idpConfig.logoutURL" size="60"/>
	
		<!--<s:checkbox label="%{getText('label.saml.idp.autoLogin')}" labelposition="left" key="autoLogin"/>-->
	
		<s:submit value="%{getText('label.saml.idp.samlRequestForm.submit')}"/>
	</s:form>
<s:include value="%{getText('app.includes.body_footer')}" />
</body>

</html>