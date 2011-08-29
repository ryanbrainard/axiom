package axiom.oauth.oauth1;

import axiom.oauth.AbstractOAuthContext;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.basic.DefaultOAuthProvider;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.exception.OAuthNotAuthorizedException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Oauth1Context extends AbstractOAuthContext {

    private String requestTokenUrl;
    private String accessTokenUrl;
    private String authUrl;
    private String authUrlWithToken;

    private String oauth_consumer_key;
    private String oauth_consumer_secret;
    private String oauth_callback;
    private String oauth_token;
    private String oauth_verifier;
    private String oauth_token_secret;

    private String sfdcLoginUrl;
    private String sfdcSessionId;
    private String sfdcServerUrl;

    private OAuthConsumer consumer;
    private OAuthProvider provider;

    public Oauth1Context(String host, String oauth_callback) {
        super(host);
        this.oauth_callback = oauth_callback;
    }

    public String getRequestTokenUrl() {
        return requestTokenUrl;
    }

    public void setRequestTokenUrl(String requestTokenUrl) {
        this.requestTokenUrl = requestTokenUrl;
    }

    public String getAccessTokenUrl() {
        return accessTokenUrl;
    }

    public void setAccessTokenUrl(String accessTokenUrl) {
        this.accessTokenUrl = accessTokenUrl;
    }

    public String getAuthUrl() {
        return authUrl;
    }

    public void setAuthUrl(String authUrl) {
        this.authUrl = authUrl;
    }

    public String getAuthUrlWithToken() {
        return authUrlWithToken;
    }

    public void setAuthUrlWithToken(String authUrlWithToken) {
        this.authUrlWithToken = authUrlWithToken;
    }

    public String getOauth_consumer_key() {
        return oauth_consumer_key;
    }

    public void setOauth_consumer_key(String oauth_consumer_key) {
        this.oauth_consumer_key = oauth_consumer_key;
    }

    public String getOauth_consumer_secret() {
        return oauth_consumer_secret;
    }

    public void setOauth_consumer_secret(String oauth_consumer_secret) {
        this.oauth_consumer_secret = oauth_consumer_secret;
    }

    public String getOauth_callback() {
        return oauth_callback;
    }

    public void setOauth_callback(String oauth_callback) {
        this.oauth_callback = oauth_callback;
    }

    public String getOauth_token() {
        return oauth_token;
    }

    public void setOauth_token(String oauth_token) {
        this.oauth_token = oauth_token;
    }

    public String getOauth_verifier() {
        return oauth_verifier;
    }

    public void setOauth_verifier(String oauth_verifier) {
        this.oauth_verifier = oauth_verifier;
    }

    public String getOauth_token_secret() {
        return oauth_token_secret;
    }

    public void setOauth_token_secret(String oauth_token_secret) {
        this.oauth_token_secret = oauth_token_secret;
    }

    public String getSfdcLoginUrl() {
        return sfdcLoginUrl;
    }

    public void setSfdcLoginUrl(String sfdcLoginUrl) {
        this.sfdcLoginUrl = sfdcLoginUrl;
    }

    public String getSfdcSessionId() {
        return sfdcSessionId;
    }

    public void setSfdcSessionId(String sfdcSessionId) {
        this.sfdcSessionId = sfdcSessionId;
    }

    public String getSfdcServerUrl() {
        return sfdcServerUrl;
    }

    public void setSfdcServerUrl(String sfdcServerUrl) {
        this.sfdcServerUrl = sfdcServerUrl;
    }


    public void retrieveRequestToken() throws OAuthExpectationFailedException, OAuthMessageSignerException, OAuthCommunicationException, OAuthNotAuthorizedException {
        refreshConsumerAndProvider();

        final String authUrlWithToken = provider.retrieveRequestToken(consumer, getOauth_callback());
        setAuthUrlWithToken(authUrlWithToken);
        setOauth_token_secret(consumer.getTokenSecret());
    }

    public void retrieveAccessToken() throws OAuthExpectationFailedException, OAuthMessageSignerException, OAuthCommunicationException, OAuthNotAuthorizedException {
        refreshConsumerAndProvider();

        provider.retrieveAccessToken(consumer, getOauth_verifier());
        setOauth_token(consumer.getToken());
        setOauth_token_secret(consumer.getTokenSecret());
    }

    public void retrieveSfdcSessionId() throws OAuthExpectationFailedException, OAuthMessageSignerException, OAuthCommunicationException, IOException {
        refreshConsumerAndProvider();

        final URL loginUrl = new URL(getSfdcLoginUrl());
        final HttpURLConnection request;
        request = (HttpURLConnection) loginUrl.openConnection();
        request.setRequestMethod("POST");

        consumer.sign(request);

        request.connect();
        final String loginResult = new Scanner(request.getInputStream()).useDelimiter("\\A").next();

        final Pattern loginResultPattern = Pattern.compile(".*<serverUrl>(.*)</serverUrl>.*<sessionId>(.*)</sessionId>.*");
        final Matcher loginResultMatcher = loginResultPattern.matcher(loginResult);
        loginResultMatcher.matches();

        setSfdcServerUrl(loginResultMatcher.group(1));
        setSfdcSessionId(loginResultMatcher.group(2));
    }

    private void refreshConsumerAndProvider() {
        consumer = new DefaultOAuthConsumer(
                getOauth_consumer_key(),
                getOauth_consumer_secret());

        provider = new DefaultOAuthProvider(
                getRequestTokenUrl(),
                getAccessTokenUrl(),
                getAuthUrl());

        consumer.setAdditionalParameters(null);
        provider.setOAuth10a(true);

        consumer.setTokenWithSecret(getOauth_token(), getOauth_token_secret());
    }
}