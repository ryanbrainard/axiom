package axiom.web;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.log4j.Logger;

import axiom.saml.idp.IdentityProvider;
import axiom.saml.idp.SamlVersion;
import axiom.saml.idp.XmlObjectSerializer;


public class SamlIdpResponseGenerator extends SamlIdpSupport {
	
	private static Logger logger = Logger.getLogger(SamlIdpResponseGenerator.class);

	private boolean autoLogin;
	
	@Override
	public String execute() throws Exception {
		IdentityProvider idp = new IdentityProvider(getIdpConfig());
		
		setRawSAMLResponse(XmlObjectSerializer.xmlObjectToString(idp.generateSamlResponse()));
		logger.debug("\nrawSAMLResponse:\n" + getRawSAMLResponse());
		
		if(getIdpConfig().getSamlVersion() == SamlVersion._1_1){
			setTarget(buildTarget());
			logger.debug("set TARGET to " + getTARGET());
		}
		
		setAutoLogin(Boolean.valueOf(getServletRequest().getParameter("autoLogin")));
		
        if (hasErrors()) {
            return INPUT;
        } else{		
        	return SUCCESS;
        }
	}

	private String buildTarget() throws MalformedURLException{
		logger.debug("Setting base TARGET URL to https://saml.salesforce.com");
		URL targetUrl = new URL("https://saml.salesforce.com");
		
		if(getIdpConfig().getSsoStartPage() != ""){
			logger.debug("appending ssoStartPage param: " + getIdpConfig().getSsoStartPage());
			targetUrl = appendUrlParam(targetUrl, "ssoStartPage", getIdpConfig().getSsoStartPage());
		}
		
		if(getIdpConfig().getStartURL() != ""){
			logger.debug("appending startURL param: " + getIdpConfig().getStartURL());
			targetUrl = appendUrlParam(targetUrl, "startURL", getIdpConfig().getStartURL());
		}
		
		if(getIdpConfig().getLogoutURL() != ""){
			logger.debug("appending logoutURL param: " + getIdpConfig().getLogoutURL());
			targetUrl = appendUrlParam(targetUrl, "logoutURL", getIdpConfig().getLogoutURL());
		}
	
		return targetUrl.toString();
	}
	
	private URL appendUrlParam(URL baseUrl, String paramName, String paramValue) throws MalformedURLException{
		if(baseUrl.getQuery() == null){
			baseUrl = new URL(baseUrl.toString() + "?");
		} else {
			baseUrl = new URL(baseUrl.toString() + "&");
		}
		
		URL newUrl = null;
		
		try {	
			newUrl = new URL(baseUrl.toString() + paramName + "=" + URLEncoder.encode(paramValue, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			logger.error(e);
		}
		
		return newUrl;
	}
	
	public String skipGeneration() throws Exception{
		return SUCCESS;
	}

	@Override
	public Breadcrumbable getParentPage() {
		return new SamlIdpHome();
	}

	
	@Override
    public boolean isAutoLogin() {
		return autoLogin;
	}


	@Override
    public void setAutoLogin(boolean autoLogin) {
		this.autoLogin = autoLogin;
	}

}
