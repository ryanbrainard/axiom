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
        xmlBeautify();
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

      function validateSubmit() {
        if (document.getElementById("recipientURL").value.indexOf("https://") != 0) {
          alert("Recipient URL must be set and start with https://");
          return false;
        }

        return true;
      }

		</script>
		
	</head>
	<body onload="init();" style="display: none;">
	
		<s:if test="%{getText('autoLogin')}"/>
		<s:else>
			<s:include value="%{getText('app.includes.body_header')}" />
		</s:else>
		
		<s:form>			
			<s:textarea id="rawSAMLResponse" label="%{getText('label.saml.idp.rawSAMLResponse')}" labelposition="top" key="rawSAMLResponse" rows="6" cols="100" onkeyup="base64EncodeAssertion(); xmlBeautify();" onBlur="base64EncodeAssertion(); xmlBeautify();" style="overflow: auto; font-family: monospace,courier; font-size: small;"/>
      <s:textarea id="prettySAMLResponse" label="%{getText('label.saml.idp.FormattedResponse')}" labelposition="top" key="prettySAMLResponse" rows="6" cols="100" style="overflow: auto; font-family: monospace,courier; font-size: small;" readonly="true"/>
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
			
			<s:submit value="%{getText('label.login')}" onclick="return validateSubmit();"/>
		</s:form>

		<s:include value="%{getText('app.includes.body_footer')}" />
	</body>
</html>
