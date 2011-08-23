package axiom.web;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author Ryan Brainard
 * @since 2011-08-22
 */
public class OAuth2WebFlowTester extends OAuthSupport {

    @Override
    public Breadcrumbable getParentPage() {
        return new OAuthHome();
    }

    public String getHost() {
        return session.containsKey("host")
                ? session.get("host").toString()
                : "login.salesforce.com";
    }

    public String getConsumerKey() {
        return session.containsKey("consumerKey")
                ? session.get("consumerKey").toString()
                : null;
    }

    public String getConsumerSecret() {
        return session.containsKey("consumerSecret")
                ? session.get("consumerSecret").toString()
                : null;
    }

    public String getRedirectUri() {
        return session.containsKey("redirectUri")
                ? session.get("redirectUri").toString()
                : getServletRequest().getRequestURL().toString();
    }

    public String getAuthUrl() throws UnsupportedEncodingException {
        return "https://" +
                getServletRequest().getParameter("host") +
                "/services/oauth2/authorize?response_type=code&display=popup&client_id=" +
                getServletRequest().getParameter("consumerKey") +
                "&redirect_uri=" +
                URLEncoder.encode(getServletRequest().getParameter("redirectUri"), "UTF-8");
    }

    public String redirectForAuthorization() {
        session.put("host", getServletRequest().getParameter("host"));
        session.put("consumerKey", getServletRequest().getParameter("consumerKey"));
        session.put("consumerSecret", getServletRequest().getParameter("consumerSecret"));

        return SUCCESS;
    }
}
