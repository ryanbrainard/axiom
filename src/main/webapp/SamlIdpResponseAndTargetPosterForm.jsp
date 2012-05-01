<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
	<head>
		<s:include value="%{getText('app.includes.head')}" />
		
		<script type="text/javascript" src="<s:url value="%{getText('app.scripts.base64')}"/>"></script>
		<script type="text/javascript" src="<s:url value="%{getText('app.scripts.urlEncodeDecode')}"/>"></script>
		<script type="text/javascript" src="<s:url value="%{getText('app.scripts.vkbeautify')}"/>"></script>
		
		<script>
			function init(){
				base64EncodeAssertion();
				updateFormAction();
				autoLogin(${autoLogin});
			}
			
			function autoLogin(value){
				if(value){
					document.getElementById("samlPoster").submit();
				} else {
					document.body.style.display = "block";
				}
			}
			
			function base64EncodeAssertion(){
				document.getElementById("SAMLResponse").value = Base64.encode(document.getElementById("rawSAMLResponse").value);
			}
			
			function updateFormAction(){
				document.getElementById("samlPoster").action = document.getElementById("recipientURL").value;
			}

			function xmlBeautify(){
				document.getElementById("prettySAMLResponse").value = vkbeautify.xml(document.getElementById("rawSAMLResponse").value);
			}

		</script>
		
	</head>
	<body onload="init();" style="display: none;">
	
		<s:if test="%{getText('autoLogin')}"/>
		<s:else>
			<s:include value="%{getText('app.includes.body_header')}" />
		</s:else>
		
		<s:form>			
			<s:textarea id="rawSAMLResponse" label="%{getText('label.saml.idp.rawSAMLResponse')}" labelposition="top" key="rawSAMLResponse" rows="6" cols="76" onkeyup="base64EncodeAssertion();" onBlur="base64EncodeAssertion();" style="overflow: auto; font-family: monospace,courier; font-size: small;"/>
		</s:form>
		
		<p/>
		
		<h2><s:text name="label.saml.idp.manualPostOverride"/></h2>
		<s:form id="samlPoster" action="" method="POST">
					
			<s:textfield id="recipientURL" key="idpConfig.recipient" label="%{getText('label.saml.idp.recipient')}" labelposition="top" size="100" onkeyup="updateFormAction();" onBlur="updateFormAction();"/>
			
			<s:textfield id="SAMLResponse" name="SAMLResponse" label="%{getText('label.saml.idp.SAMLResponse')}" labelposition="top" size="100"/>
						
			<s:if test="%{TARGET != null}">
				<s:textfield name="TARGET" key="TARGET" label="%{getText('label.saml.idp.target')}" labelposition="top" size="100"/>
			</s:if>
			<s:else>
				<s:textfield name="RelayState" key="RelayState" label="%{getText('label.saml.idp.RelayState')}" labelposition="top" size="100"/>
			</s:else>
			
			<s:submit value="%{getText('label.login')}"/>
		</s:form>

		<p/>
		<h2><s:text name="page.formatresponse"/></h2>
		<s:form>
			<input type="button" onclick="xmlBeautify();" value="<s:text name="label.saml.idp.FormatResponse"/>" />
			<s:textarea id="prettySAMLResponse" label="%{getText('label.saml.idp.FormattedResponse')}" labelposition="top" key="prettySAMLResponse" rows="12" cols="76" style="overflow: auto; font-family: monospace,courier; font-size: small;"/>
		</s:form>
		
		<s:include value="%{getText('app.includes.body_footer')}" />
	</body>
</html>
