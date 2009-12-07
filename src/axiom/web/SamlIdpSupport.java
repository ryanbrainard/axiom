package axiom.web;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import axiom.saml.idp.IdpConfiguation;
import axiom.saml.idp.SamlUserIdLocation;
import axiom.saml.idp.SamlVersion;

public abstract class SamlIdpSupport extends AxiomSupport{
	
    @SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(SamlIdpSupport.class);
	
	private IdpConfiguation idpConfig = new IdpConfiguation();
	private String rawSAMLResponse = null;
	private String target;
	private boolean autoLogin = false;
		
		
	public IdpConfiguation getIdpConfig() {
		String keystoreFile = getServletContext().getRealPath("/") + getServletContext().getInitParameter("keystoreFile");
		if(keystoreFile != null && !keystoreFile.equals("")){
			idpConfig.setKeystoreFile(new File(keystoreFile));
		}
		idpConfig.setKeystoreAlias(getServletContext().getInitParameter("keystoreAlias"));
		idpConfig.setKeystorePassword(getServletContext().getInitParameter("keystorePassword").toCharArray());
		idpConfig.setKeystoreAliasPassword(getServletContext().getInitParameter("keystoreAliasPassword").toCharArray());
		
		return idpConfig;
	}
	public void setIdpConfig(IdpConfiguation idpConfig) {
		this.idpConfig = idpConfig;
	}
	
	
	public String getRawSAMLResponse() {
		return rawSAMLResponse;
	}
	
	public void setRawSAMLResponse(String rawSAMLResponse) {
		this.rawSAMLResponse = rawSAMLResponse;
	}

	//all CAPS getter for buggy Struts
	public String getTARGET() {
		return target;
	}

	public String getTarget() {
		return target;
	}
	
	public void setTarget(String target) {
		this.target = target;
	}	
	
	public boolean isAutoLogin() {
		return autoLogin;
	}
	
	public void setAutoLogin(boolean autoLogin) {
		this.autoLogin = autoLogin;
	}

	public Map<SamlVersion, String> getSamlVersions(){
		Map<SamlVersion, String> versions = new LinkedHashMap<SamlVersion, String>();
			versions.put(SamlVersion._1_1, "1.1");
			versions.put(SamlVersion._2_0, "2.0");
		return versions;
	}
	
	public Map<SamlUserIdLocation, String> getSamlUserIdLocations(){
		Map<SamlUserIdLocation, String> locations = new LinkedHashMap<SamlUserIdLocation, String>();
			locations.put(SamlUserIdLocation.SUBJECT, getText("label.saml.idp.samlUserIdLocation.values.subject"));		
			locations.put(SamlUserIdLocation.ATTRIBUTE, getText("label.saml.idp.samlUserIdLocation.values.attribute"));
		return locations;
	}
	
	//properties for RelayState that are stored in startUrl
	public String getRelayState() {
		return getIdpConfig().getStartURL();
	}
	public void setRelayState(String relayState) {
		getIdpConfig().setStartURL(relayState);
	}


}
