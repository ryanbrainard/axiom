/**
 * SamlIdpSoapBindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package axiom.soap;

import java.io.File;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import axiom.saml.idp.*;
import org.apache.axis.MessageContext;
import org.apache.axis.transport.http.HTTPConstants;
import org.apache.log4j.Logger;
import org.opensaml.xml.io.MarshallingException;
import org.opensaml.xml.util.Base64;

import axiom.saml.idp.IdpConfiguration;


public class SamlIdpSoapBindingImpl implements axiom.soap.SamlIdp{
	
	private static Logger logger = Logger.getLogger(SamlIdpSoapBindingImpl.class);
	
    public java.lang.String generateSamlResponse(axiom.soap.AxiomSamlRequest axiomSamlRequest) throws java.rmi.RemoteException {
		
    	logger.debug("entering SOAP-based generateSamlResponse implementation");
    	
    	IdpConfiguration idpConfig = new IdpConfiguration();
			
    		logger.debug("Getting servlet context");
    		HttpServlet servlet = (HttpServlet)MessageContext.getCurrentContext().getProperty(HTTPConstants.MC_HTTP_SERVLET);
    		ServletConfig servletConfig = servlet.getServletConfig();
    		ServletContext servletContext = servletConfig.getServletContext();
    	
    		logger.debug("configuring keystore");
    		String keystoreFile = servletContext.getInitParameter("keystoreFile");
			if(keystoreFile != null && !keystoreFile.equals("")){
				idpConfig.setKeystoreFile(new File(servletContext.getRealPath("/") + keystoreFile));
			}
			idpConfig.setKeystoreAlias(servletContext.getInitParameter("keystoreAlias"));
			idpConfig.setKeystorePassword(servletContext.getInitParameter("keystorePassword").toCharArray());
			idpConfig.setKeystoreAliasPassword(servletContext.getInitParameter("keystoreAliasPassword").toCharArray());
    	
			logger.debug("configuring idp with request from SOAP API");
    		idpConfig.setSamlVersion(axiom.saml.idp.SamlVersion.valueOf(axiomSamlRequest.getSamlVersion().getValue()));
    		idpConfig.setIssuer(axiomSamlRequest.getIssuer());
    		idpConfig.setRecipient(axiomSamlRequest.getRecipient());
    		idpConfig.setUserId(axiomSamlRequest.getUserId());
    		idpConfig.setSamlUserIdLocation(axiom.saml.idp.SamlUserIdLocation.valueOf(axiomSamlRequest.getSamlUserIdLocation().getValue()));
    		idpConfig.setAttributeName(axiomSamlRequest.getAttributeName());
    		idpConfig.setAttributeUri(axiomSamlRequest.getAttributeUri());
    		
  
    	logger.debug("Calling IdP to create SAML response");
		String rawSamlResponse = null;
		try {
			rawSamlResponse = XmlObjectSerializer.xmlObjectToString(new IdentityProvider(idpConfig).generateSamlResponse());
		} catch (MarshallingException e) {
			throw new RuntimeException(e);
		} catch (TransformerFactoryConfigurationError e) {
			throw new RuntimeException(e);
		} catch (TransformerException e) {
			throw new RuntimeException(e);
		}
				
		if(axiomSamlRequest.getBase64EncodeResponse() != null && axiomSamlRequest.getBase64EncodeResponse()==false){
			logger.debug("Returning plain text SAMLResponse via SOAP");
			return rawSamlResponse;
		} else {
			logger.debug("Returning Base 64 encoded SAMLResponse via SOAP");
			return Base64.encodeBytes(rawSamlResponse.getBytes());
		}
		
    }

}
