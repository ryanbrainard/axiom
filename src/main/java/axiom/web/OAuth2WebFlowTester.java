package axiom.web;

import axiom.oauth.oauth2.Oauth2Context;
import com.opensymphony.xwork2.ActionContext;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.HttpStatus;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ryan Brainard
 * @since 2011-08-22
 */
public class OAuth2WebFlowTester extends OAuthSupport {

    private static final String OAUTH_CONTEXT = "oauthContext";

    @Override
    public Breadcrumbable getParentPage() {
        return new OAuthHome();
    }

    public String redirectForAuthorization() throws UnsupportedEncodingException {
        if (getOauthContext().getAuthRequestUrl() == null) {
            addActionError("Must provide an Authorization URL");
            return INPUT; //TODO: should be error?
        }

        return SUCCESS;
    }

    public String handleAuthorizationCode() throws URISyntaxException {
        getOauthContext().setFieldsFrom(sanitizeParameterArrays(ActionContext.getContext().getParameters()));

        if (getOauthContext().getError() != null) {
            addActionError(getOauthContext().getError() + ": " + getOauthContext().getError_description());
            return ERROR;
        }

        if (getOauthContext().getCode() == null || "".equals(getOauthContext().getCode())) {
            addActionError("Code not set");
            return ERROR;
        }

        return SUCCESS;
    }

    public String requestAccessToken() {
        final HttpClient client = new HttpClient();
        final PostMethod tokenRequest = new PostMethod(getOauthContext().getTokenRequestUrl());
        try {
            int statusCode = client.executeMethod(tokenRequest);

            if (statusCode != HttpStatus.SC_OK) {
                throw new Exception(tokenRequest.getStatusLine() + "\n" + new String(tokenRequest.getResponseBody()));
            }

            //noinspection unchecked
            getOauthContext().setFieldsFrom((JSONObject) JSONSerializer.toJSON(new String(tokenRequest.getResponseBody())));

            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
            return ERROR;
        } finally {
            tokenRequest.releaseConnection();
        }
    }

    public Oauth2Context getOauthContext() {
        if (!session.containsKey(OAUTH_CONTEXT)) {
            final String host = "login.salesforce.com";

            String requestURL = getServletRequest()
                    .getRequestURL()
                    .toString()
                    .replaceFirst(ActionContext.getContext().getName(), "OAuth2HandleAuthCode")
                    .replaceFirst(".jsp", ".action");

            if (!requestURL.contains("localhost")) {
                requestURL = requestURL.replaceFirst("http://", "https://");
            }

            session.put(OAUTH_CONTEXT, new Oauth2Context(host, requestURL));
        }

        return (Oauth2Context) session.get(OAUTH_CONTEXT);
    }

    private static Map<String, String> sanitizeParameterArrays(Map<String, Object> params) {
        Map<String, String> sanitizedParams = new HashMap<String, String>();
        for (Map.Entry<String, Object> p : params.entrySet()) {
            sanitizedParams.put(p.getKey(), ((String[]) p.getValue())[0]);
        }
        return sanitizedParams;
    }

}
