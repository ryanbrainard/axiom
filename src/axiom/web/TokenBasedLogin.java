package axiom.web;

import axiom.delauth.token.TokenGenerator;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;


public class TokenBasedLogin extends AxiomSupport {

    @SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(TokenBasedLogin.class);

	
	private String username;
	private String instance;
	private String baseLoginUrl;
	private String loginUrl;
	private String token;
	private boolean autoLogin;
	private String ssoStartPage;
	private String startURL;
	private boolean portalLogin;
	
	@Override
	public Breadcrumbable getParentPage() {
		return new Home();
	}
	
	@Override
	public String execute() throws Exception {		
		if(username == null || instance == null || username == "" || instance == ""){
			setStartURL(getServletRequest().getParameter("startURL"));
			return INPUT;
		}
		
		System.out.println(startURL);
		System.out.println(username);
		
		token = new TokenGenerator().generateToken(username);
		
		baseLoginUrl = "https://" + instance + ".salesforce.com/";
		
		if(portalLogin){
			baseLoginUrl += "secur/login_portal.jsp";			
		} else {
			baseLoginUrl += "login.jsp";
		}
		
		setSsoStartPage(getServletRequest().getRequestURL().toString());
		
		if(autoLogin){
			return "autoLogin";	
		} else {
			return "displayToken";	
		}	
		
	}
	
	public boolean isPortalLogin() {
		return portalLogin;
	}

	public void setPortalLogin(boolean portalLogin) {
		this.portalLogin = portalLogin;
	}
	
	public String getSsoStartPage(){
		return ssoStartPage;
	}
	
	public void setSsoStartPage(String ssoStartPage){
		this.ssoStartPage = ssoStartPage;
	}
	
	public String getStartURL() {
		return startURL;
	}

	public void setStartURL(String startURL) {
		this.startURL = startURL;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getInstance() {
		return instance;
	}

	public void setInstance(String instance) {
		this.instance = instance;
	}

	public String getLoginUrl() {
		return loginUrl;
	}

	public String getBaseLoginUrl() {
		return baseLoginUrl;
	}

	public boolean isAutoLogin() {
		return autoLogin;
	}

	public void setAutoLogin(boolean autoLogin) {
		this.autoLogin = autoLogin;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	public String getToken() {
		return token;
	}
	
	public String getServiceEndpoint(){
		return getAxiomEndpoint() + "/services/AuthenticationService";
	}
	
	public Map<Boolean, String> getAutoLoginOptions(){
		Map<Boolean, String> options = new LinkedHashMap<Boolean, String>();
			options.put(false, "Show Token");	
			options.put(true, "Auto Login");
		return options;
	}
	
}
