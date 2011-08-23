package axiom.web;

import axiom.oauth.OauthRequester;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLEncoder;

/**
 * @author Ryan Brainard
 * @since 2011-08-22
 */
public class OAuth2WebFlowTester extends OAuthSupport {

    private String authUrl;
    private String authCode;

    @Override
    public Breadcrumbable getParentPage() {
        return new OAuthHome();
    }

    //TODO: move these all to a bean ?
    public String getHost() {
        return getFromSession("host", "login.salesforce.com");
    }

    public String getConsumerKey() {
        return getFromSession("consumerKey", null);
    }

    public String getConsumerSecret() {
        return getFromSession("consumerSecret", null);
    }

    public String getRedirectUri() throws MalformedURLException {
        final String requestURL = getServletRequest().getRequestURL().toString();
        final String defaultHandlerUrl = requestURL.replaceFirst(this.getClass().getSimpleName() + ".jsp", "OAuth2HandleAuthCode.action");
        return getFromSession("redirectUri",  defaultHandlerUrl);
    }

    public String getAuthUrl() {
        return authUrl;
    }

    public String getAuthCode() {
        return authCode;
    }

    public String redirectForAuthorization() throws UnsupportedEncodingException {
        authUrl = "https://" +
                getRequestParamWithSessionStorage("host") +
                "/services/oauth2/authorize?response_type=code&display=popup&client_id=" +
                getRequestParamWithSessionStorage("consumerKey") +
                "&redirect_uri=" +
                URLEncoder.encode(getRequestParamWithSessionStorage("redirectUri"), "UTF-8");

        return SUCCESS;
    }

    public String handleAuthorizationCode() {
        authCode = getServletRequest().getParameter("code");
        return SUCCESS;
    }

    public String requestAccessToken() throws Exception {
        final OauthRequester oReq = new OauthRequester();
        // todo: this is not a "host" ...
		oReq.setHost("https://" + getFromSession("host") + "/services/oauth2/authorize");
		oReq.setResponseType("code");
		oReq.setClientKey(getFromSession("consumerKey"));
		oReq.setCallbackUri(getFromSession("redirectUri"));
		oReq.setState("state"); //todo: do we ever need this??
		oReq.setClientSecret(getRequestParamWithSessionStorage("consumerSecret"));
		oReq.setAuthorizationCode(getServletRequest().getParameter("authCode"));

        oReq.generateAccessToken();

        return SUCCESS;
    }

    // todo: i'm sure struts has some better way to do this
    private String getRequestParamWithSessionStorage(String key) {
        final String parameter = getServletRequest().getParameter(key);
        session.put(key, parameter);
        return parameter;
    }

    // todo: i'm sure struts has some better way to do this
    private String getFromSession(String key) {
        if (!session.containsKey(key)) {
            throw new IllegalStateException(key + " not in session");
        }

        return session.get(key).toString();
    }

    // todo: i'm sure struts has some better way to do this
    private String getFromSession(String key, String defaultValue) {
        return session.containsKey(key)
                ? session.get(key).toString()
                : defaultValue;
    }
}
