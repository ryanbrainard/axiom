<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
	<s:include value="%{getText('app.includes.head')}" />
</head>

<body>
	<s:include value="%{getText('app.includes.body_header')}" />
		
		<ul>
			<li><span class="mainmenu"><a href="DownloadIdpCert.action"><s:text name="label.configure"/></a></span><br/>
			Install this IdP's certificate in the Salesforce org for login to via SAML:</li>
				<ul>
					<li><a href="DownloadIdpCert.action">Download the Identity Provider Certificate</a></li>
					<li>In Salesforce, go to <em>Setup | Security Controls | Single Sign-On Settings | Federated single sign-on using SAML</em></li>
					<li>Install the dowloaded certificate in the Identity Provider Certificate field</li>
					<li>Configure the Issuer, User Id Type, and User Id Location settings. These values are arbitrary, but must be matched when generating the SAML Response in the next step.</li>
				</ul>
				
			<li><span class="mainmenu"><a href="RequestSamlResponse.action"><s:text name="label.generate"/></a></span><br/>
				<p>Matching the configuration set in Salesforce above, <a href="RequestSamlResponse.action">generate a SAML Response</a>. If you already have
				a SAML Response, skip this step.</p>
				<p>Alternatively, you can perform this request via the Axiom API. <a href="${SamlIdpServiceEndpoint}?wsdl">Download the WSDL</a> for a description of this service.</p>
			</li>
				
			<li><span class="mainmenu"><a href="SamlIdpResponseAndTargetPosterForm.action"><s:text name="label.login"/></a></span><br/>
			Make any modifications to the generated Response and/or Target values and <a href="SamlIdpResponseAndTargetPosterForm.action">submit to Salesforce to initiate a SAML login</a></li>
		</ul>
		
	<s:include value="%{getText('app.includes.body_footer')}" />
</body>

</html>