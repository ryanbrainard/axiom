package axiom.oauth2;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

import java.lang.reflect.Field;
import java.net.URI;
import java.util.Map;

public class OauthContext {

    private String host;
    private String authRequestUrl;
    private String tokenRequestUrl;

    private String client_id;
    private String client_secret;
    private String response_type;
    private String redirect_uri;
    private String display;
    private String code;
    private String grant_type;
    private String issued_at;
    private String refresh_token;
    private String instance_url;
    private String signature;
    private String access_token;
    private String id;
    private String error;
    private String error_description;

    public OauthContext(String host, String redirect_uri) {
        this.host = host;
        this.redirect_uri = redirect_uri;
    }

    public void setFieldsFrom(URI uri) {
        for (NameValuePair param : URLEncodedUtils.parse(uri, "UTF-8")) {
            for (Field field : this.getClass().getDeclaredFields()) {
                if (field.getName().equals(param.getName())) {
                    try {
                        field.set(this, param.getValue());
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    public void setFieldsFrom(Map<String, String> map) {
        for (Field field : this.getClass().getDeclaredFields()) {
            if (map.containsKey(field.getName())) {
                try {
                    field.set(this, map.get(field.getName()));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getAuthRequestUrl() {
        return authRequestUrl;
    }

    public void setAuthRequestUrl(String authRequestUrl) {
        this.authRequestUrl = authRequestUrl;
    }

    public String getTokenRequestUrl() {
        return tokenRequestUrl;
    }

    public void setTokenRequestUrl(String tokenRequestUrl) {
        this.tokenRequestUrl = tokenRequestUrl;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getResponse_type() {
        return response_type;
    }

    public void setResponse_type(String response_type) {
        this.response_type = response_type;
    }

    public String getRedirect_uri() {
        return redirect_uri;
    }

    public void setRedirect_uri(String redirect_uri) {
        this.redirect_uri = redirect_uri;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }

    public String getIssued_at() {
        return issued_at;
    }

    public void setIssued_at(String issued_at) {
        this.issued_at = issued_at;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getInstance_url() {
        return instance_url;
    }

    public void setInstance_url(String instance_url) {
        this.instance_url = instance_url;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError_description() {
        return error_description;
    }

    public void setError_description(String error_description) {
        this.error_description = error_description;
    }
}