package axiom.web;

import axiom.oauth.oauth1.Oauth1Context;
import com.opensymphony.xwork2.ActionContext;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ryan Brainard
 * @since 2011-08-22
 */
public class OAuth1FlowTester extends OAuthSupport {

    private static final String OAUTH_CONTEXT = "oauthContext";

    @Override
    public Breadcrumbable getParentPage() {
        return new OAuthHome();
    }

    public String requestRequestToken() {
        // TODO: arg checking before this?

        try {
            getOauthContext().retrieveRequestToken();
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
            return ERROR;
        }
    }

    public String redirectForAuthorization() {
        if (getOauthContext().getAuthUrlWithToken() == null || "".equals(getOauthContext().getAuthUrlWithToken())) {
            addActionError("Authorization URL must be populated");
            return INPUT;
        }

        return SUCCESS;
    }

    public String handleCallback() {
        getOauthContext().setFieldsFrom(sanitizeParameterArrays(ActionContext.getContext().getParameters()));
        return SUCCESS;
    }

    public String requestAccessToken() {
        try {
            getOauthContext().retrieveAccessToken();

            // prep for next page -- TODO: is this the best place for this?
            getOauthContext().setSfdcLoginUrl("https://" + getOauthContext().getHost() + "/services/OAuth/u/22.0");
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
            return ERROR;
        }

        return SUCCESS;
    }

    public String loginToSfdc() {
        try {
            getOauthContext().retrieveSfdcSessionId();
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
            return ERROR;
        }

        return SUCCESS;
    }

    // TODO: pull to super class and share?
    public Oauth1Context getOauthContext() {
        if (!session.containsKey(OAUTH_CONTEXT)) {
            final String host = "login.salesforce.com";

            final String defaultCallbackUrl = getServletRequest()
                    .getRequestURL()
                    .toString()
                    .replaceFirst("http://", "https://")
                    .replaceFirst(ActionContext.getContext().getName(), "OAuth1HandleCallback")
                    .replaceFirst(".jsp", ".action");

            session.put(OAUTH_CONTEXT, new Oauth1Context(host, defaultCallbackUrl));
        }

        return (Oauth1Context) session.get(OAUTH_CONTEXT);
    }

    // TODO: pull to super class and share?
    private static Map<String, String> sanitizeParameterArrays(Map<String, Object> params) {
        Map<String, String> sanitizedParams = new HashMap<String, String>();
        for (Map.Entry<String, Object> p : params.entrySet()) {
            sanitizedParams.put(p.getKey(), ((String[]) p.getValue())[0]);
        }
        return sanitizedParams;
    }
}
