package axiom.oauth2;

import junit.framework.TestCase;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import java.net.URI;


public class OAuthContextTest extends TestCase {

    public void testFlow() throws Exception {
        final OauthContext context = new OauthContext("HOST", "REDIRECT_URI");
        assertEquals("HOST", context.getHost());
        assertEquals("REDIRECT_URI", context.getRedirect_uri());

        // consumer builds authRequestUrl on their own
        final URI authRequestUrl = new URI("https://login.salesforce.com/services/oauth2/authorize" +
                "?response_type=RESPONSE_TYPE" +
                "&display=DISPLAY" +
                "&client_id=CLIENT_ID" +
                "&redirect_uri=REDIRECT_URI");

        // add the request to the context
        context.setFieldsFrom(authRequestUrl);
        assertEquals("RESPONSE_TYPE", context.getResponse_type());
        assertEquals("DISPLAY", context.getDisplay());
        assertEquals("CLIENT_ID", context.getClient_id());
        assertEquals("REDIRECT_URI", context.getRedirect_uri());

        // user is redirected to authRequestUrl
        // user logins in
        // SFDC redirects back to consumer
        final URI authResponseUrl = new URI("http://localhost:8080/something" +
                "?display=DISPLAY" +
                "&code=CODE");

        // add the response to the context
        context.setFieldsFrom(authResponseUrl);
        assertEquals("DISPLAY", context.getDisplay());
        assertEquals("CODE", context.getCode());

        // consumer builds tokenRequestUrl on their own
        final URI tokenRequestUrl = new URI("https://login.salesforce.com/services/oauth2/token" +
                "?&grant_type=GRANT_TYPE" +
                "&client_id=CLIENT_ID" +
                "&code=CODE" +
                "&client_secret=CLIENT_SECRET" +
                "&redirect_uri=REDIRECT_URI");

        // update the context with this URL
        context.setFieldsFrom(tokenRequestUrl);
        assertEquals("GRANT_TYPE", context.getGrant_type());
        assertEquals("CLIENT_ID", context.getClient_id());
        assertEquals("CODE", context.getCode());
        assertEquals("CLIENT_SECRET", context.getClient_secret());
        assertEquals("REDIRECT_URI", context.getRedirect_uri());


        // client sends token request on back channel and gets back JSON
        final JSONObject tokenResponse = (JSONObject) JSONSerializer.toJSON(
                "{'" +
                        "id':'ID'," +
                        "'issued_at':'ISSUED_AT'," +
                        "'refresh_token':'REFRESH_TOKEN'," +
                        "'instance_url':'INSTANCE_URL'," +
                        "'signature':'SIGNATURE'," +
                        "'access_token':'ACCESS_TOKEN'" +
                        "}");

        // add json to context
        context.setFieldsFrom(tokenResponse);
        assertEquals("ID", context.getId());
        assertEquals("ISSUED_AT", context.getIssued_at());
        assertEquals("REFRESH_TOKEN", context.getRefresh_token());
        assertEquals("INSTANCE_URL", context.getInstance_url());
        assertEquals("SIGNATURE", context.getSignature());
        assertEquals("ACCESS_TOKEN", context.getAccess_token());
    }
}
