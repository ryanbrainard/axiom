package axiom.oauth;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

public class OauthJSonParser {

    private String id;
	private String accessToken;
    private String issuedAt;
    private String refreshToken;
    private String instanceUrl;
    
    public OauthJSonParser(String jsonTxt) throws Exception {
    	
    	if(jsonTxt == null || jsonTxt.length() ==0 )
    			throw new Exception("Invalid JSON String");
    	
    	JSONObject json = (JSONObject) JSONSerializer.toJSON( jsonTxt );  
    	
    	setId(json.getString("id"));
    	setAccessToken(json.getString("access_token"));
    	setIssuedAt(json.getString("issued_at"));
    	setRefreshToken(json.getString("refresh_token"));
    	setInstanceUrl(json.getString("instance_url"));
    	       
    }
    
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getIssuedAt() {
		return issuedAt;
	}

	public void setIssuedAt(String issuedAt) {
		this.issuedAt = issuedAt;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getInstanceUrl() {
		return instanceUrl;
	}

	public void setInstanceUrl(String instanceUrl) {
		this.instanceUrl = instanceUrl;
	}
    
    
}