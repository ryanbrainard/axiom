package axiom.web;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author Ryan Brainard
 * @since 2011-08-22
 */
public class OAuth2WebFlowTester extends OAuthSupport {

    private String authUrl;

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

    public String getRedirectUri() {
        return getFromSession("redirectUri",  getServletRequest().getRequestURL().toString());
    }

    public String getAuthUrl() {
        return authUrl;
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

    // todo: i'm sure struts has some better way to do this
    private String getRequestParamWithSessionStorage(String key) {
        final String parameter = getServletRequest().getParameter(key);
        session.put(key, parameter);
        return parameter;
    }

    // todo: i'm sure struts has some better way to do this
    private String getFromSession(String key, String defaultValue) {
        return session.containsKey(key)
                ? session.get(key).toString()
                : defaultValue;
    }
}
