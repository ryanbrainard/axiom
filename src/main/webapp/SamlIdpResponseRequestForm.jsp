<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
	<s:include value="%{getText('app.includes.head')}" />

	<script type="text/javascript">
	     
		function evalAttribInputsVisibility() {
			if(document.getElementById('GenerateSamlResponse_action_idpConfig_samlUserIdLocationATTRIBUTE').checked){
				document.getElementById('GenerateSamlResponse_action_idpConfig_attributeName').disabled=false;
				document.getElementById('GenerateSamlResponse_action_idpConfig_attributeUri').disabled=false;
			} else {
				document.getElementById('GenerateSamlResponse_action_idpConfig_attributeName').disabled=true;
				document.getElementById('GenerateSamlResponse_action_idpConfig_attributeUri').disabled=true;
			}
		}

		function setPortalValues() {
            if (document.getElementById('GenerateSamlResponse_action_idpConfig_samlVersion').value == "_1_1") {
                document.getElementById('GenerateSamlResponse_action_idpConfig_userTypePORTAL').disabled = true;
                document.getElementById('GenerateSamlResponse_action_idpConfig_userTypeSITE').disabled = true;
                document.getElementById('GenerateSamlResponse_action_idpConfig_userTypeSTANDARD').checked=true;
            } else {
                document.getElementById('GenerateSamlResponse_action_idpConfig_userTypePORTAL').disabled = false;
                document.getElementById('GenerateSamlResponse_action_idpConfig_userTypeSITE').disabled = false;
            }
            
			if (document.getElementById('GenerateSamlResponse_action_idpConfig_userTypePORTAL').checked) {
				document.getElementById('GenerateSamlResponse_action_idpConfig_orgId').disabled=false;
				document.getElementById('GenerateSamlResponse_action_idpConfig_portalId').disabled=false;
				document.getElementById('GenerateSamlResponse_action_idpConfig_siteURL').disabled=true;
			} else if(document.getElementById('GenerateSamlResponse_action_idpConfig_userTypeSITE').checked) {
				document.getElementById('GenerateSamlResponse_action_idpConfig_orgId').disabled=false;
				document.getElementById('GenerateSamlResponse_action_idpConfig_portalId').disabled=false;
				document.getElementById('GenerateSamlResponse_action_idpConfig_siteURL').disabled=false;
			} else {
				document.getElementById('GenerateSamlResponse_action_idpConfig_userTypeSTANDARD').checked=true;
				document.getElementById('GenerateSamlResponse_action_idpConfig_orgId').disabled=true;
				document.getElementById('GenerateSamlResponse_action_idpConfig_portalId').disabled=true;
				document.getElementById('GenerateSamlResponse_action_idpConfig_siteURL').disabled=true;
			}
		}
		
	</script>
</head>

<body onload="evalAttribInputsVisibility(); setPortalValues();">
	<s:include value="%{getText('app.includes.body_header')}" />

      <div id="pageIntro">
           <s:text name="page.additionalattributes"/>
      </div>
      <br/><br/>


	<s:form method="POST" action="GenerateSamlResponse.action">
		<s:select label="%{getText('label.saml.idp.samlVersion')}" key="idpConfig.samlVersion" list="samlVersions" onchange="setPortalValues();"/>
		
		<s:textfield label="%{getText('label.saml.idp.userId')}" key="idpConfig.userId" size="60"/>
		
		<s:radio label="%{getText('label.saml.idp.samlUserIdLocation')}" key="idpConfig.samlUserIdLocation" list="samlUserIdLocations" value="%{getText('default.saml.idp.samlUserIdLocation')}" onclick="evalAttribInputsVisibility();"/>
	
	      <s:textfield label="%{getText('label.saml.idp.attributeName')}" key="idpConfig.attributeName" size="60"/>
	    
	      <s:textfield label="%{getText('label.saml.idp.attributeUri')}" key="idpConfig.attributeUri" size="60"/>

		<s:textfield label="%{getText('label.saml.idp.issuer')}" key="idpConfig.issuer" size="60"/>
	
		<s:textfield label="%{getText('label.saml.idp.recipient')}" key="idpConfig.recipient" size="60"/>
		
		<s:textfield label="%{getText('label.entityId')}" key="idpConfig.audience" size="60"/>
		
		<s:textfield label="%{getText('label.ssoStartPage')}" key="idpConfig.ssoStartPage" size="60"/>

		<s:textfield label="%{getText('label.saml.idp.RelayStateOrStartURL')}" key="idpConfig.startURL" size="60"/>
		
		<s:textfield label="%{getText('label.logoutURL')}" key="idpConfig.logoutURL" size="60"/>
		
		<s:radio label="%{getText('label.saml.idp.userType')}" key="idpConfig.userType" list="userTypes" value="%{getText('default.saml.idp.userType')}" onclick="setPortalValues();" />
	
		<s:textfield label="%{getText('label.orgId')}" key="idpConfig.orgId" size="60"/>
		
		<s:textfield label="%{getText('label.portalId')}" key="idpConfig.portalId" size="60"/>
		
		<s:textfield label="%{getText('label.siteURL')}" key="idpConfig.siteURL" size="60"/>

            <s:textarea label="%{getText('label.additionalAttributes')}" key="idpConfig.additionalAttributes" rows="10"  style="overflow: auto; font-family: monospace,courier; font-size: small; width: 396px;"/>
	
		<!--<s:checkbox label="%{getText('label.saml.idp.autoLogin')}" labelposition="left" key="autoLogin"/>-->
	
		<s:submit value="%{getText('label.saml.idp.samlRequestForm.submit')}"/>
	</s:form>
<s:include value="%{getText('app.includes.body_footer')}" />
</body>

</html>