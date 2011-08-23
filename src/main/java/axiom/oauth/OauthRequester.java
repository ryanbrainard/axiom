package axiom.oauth;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.IOException;

public class OauthRequester {

	private String host;
	private String responseType;
	private String clientKey;
	private String callbackUri;
	private String state;
	private String clientSecret;
	private String authorizationCode;
	private String accessToken;
	
	private String jSonResponse;

	

	public String generateAuthorizationHostString() throws Exception {
		String hostString = "";

		if (getHost() == null || getHost().length() == 0)
			throw new Exception("Invalid Host Name");
		if (getClientKey() == null || getClientKey().length() == 0)
			throw new Exception("Invalid Client Key");
		if (getCallbackUri() == null || getCallbackUri().length() == 0)
			throw new Exception("Invalid Callback URI");

		hostString += getHost() +"/services/oauth2/authorize";
		hostString += "?client_id=" + getClientKey();
		hostString += "&redirect_uri=" + getCallbackUri();
		hostString += "&response_type=" +getResponseType();
		hostString += "&state="+getState();

		return hostString;
	}

	public String generateAccessHostString() throws Exception {
		String hostString = "";
		if (getHost() == null || getHost().length() == 0)
			throw new Exception("Invalid Host Name");
		if (getClientKey() == null || getClientKey().length() == 0)
			throw new Exception("Invalid Client Key");
		if (getCallbackUri() == null || getCallbackUri().length() == 0)
			throw new Exception("Invalid Callback URI");
		if (getAuthorizationCode() == null
				|| getAuthorizationCode().length() == 0)
			throw new Exception("Invalid Authorization Code");

		hostString =  getHost()+"/services/oauth2/token?&grant_type=authorization_code";
		hostString += "&client_id=" + getClientKey();
		hostString += "&redirect_uri=" + getCallbackUri();
		hostString += "&code=" + getAuthorizationCode();
		hostString += "&client_secret=" + getClientSecret();
		return hostString;
	}

	public void generateAccessToken(String url) throws Exception {

		HttpClient client = new HttpClient();
//		System.out.println(generateAccessHostString());
		PostMethod method = new PostMethod(url);
//		PostMethod method = new PostMethod(generateAccessHostString());
	//	String header = "OAuth " + getAccessToken();
	//	method.setRequestHeader("Authorization", header);

		try {
			int statusCode = client.executeMethod(method);

			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + method.getStatusLine() + new String(method.getResponseBody()));
				throw new Exception("Method failed: " + method.getStatusLine() + new String(method.getResponseBody()));
			}
			byte[] responseBody = method.getResponseBody();
			
			setjSonResponse(new String(responseBody));
		

		} catch (HttpException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			System.err.println("IO Exception Occurred " + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			method.releaseConnection();
		}

	}
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getResponseType() {
		return responseType;
	}

	public void setResponseType(String responseType) {
		this.responseType = responseType;
	}

	public String getClientKey() {
		return clientKey;
	}

	public void setClientKey(String clientKey) {
		this.clientKey = clientKey;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public String getCallbackUri() {
		return callbackUri;
	}

	public void setCallbackUri(String callbackUri) {
		this.callbackUri = callbackUri;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAuthorizationCode() {
		return authorizationCode;
	}

	public void setAuthorizationCode(String authorizationCode) {
		this.authorizationCode = authorizationCode;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	
	public String getjSonResponse() {
		return jSonResponse;
	}

	public void setjSonResponse(String jSonResponse) {
		this.jSonResponse = jSonResponse;
	}
	

}
