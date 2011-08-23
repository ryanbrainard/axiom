package axiom.web;

import axiom.oauth.OauthJSonParser;
import axiom.oauth.OauthRequester;
import com.sun.tools.javac.code.Scope;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.Collection;

/**
 * @author Ryan Brainard
 * @since 2011-08-22
 */
public class OAuth2WebFlowTester extends OAuthSupport {

    private String authUrl;
    private String authCode;
    private OauthJSonParser result;

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
        String requestURL = getServletRequest().getRequestURL().toString();

        if (!requestURL.contains("localhost")) {
            requestURL = requestURL.replaceFirst("http", "https");
        }

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
        getRequestParamWithSessionStorage("host");
        getRequestParamWithSessionStorage("consumerKey");
        getRequestParamWithSessionStorage("redirectUri");

        authUrl = getServletRequest().getParameter("authUrl");

        if (authUrl == null) {
            return INPUT;
        }

        return SUCCESS;
    }

    public String handleAuthorizationCode() {
        authCode = getServletRequest().getParameter("code");
        return SUCCESS;
    }

    public String requestAccessToken() throws Exception {
        try {
            getRequestParamWithSessionStorage("consumerSecret");
            final OauthRequester oReq = new OauthRequester();
            oReq.generateAccessToken(getServletRequest().getParameter("accessTokenUrl"));
            result = new OauthJSonParser(oReq.getjSonResponse());
            return SUCCESS;
        } catch (Exception e) {
            addActionError(e.getMessage());
            authCode = getServletRequest().getParameter("authCode");
            return ERROR;
        }
    }

    public OauthJSonParser getResult() {
        return result;
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
