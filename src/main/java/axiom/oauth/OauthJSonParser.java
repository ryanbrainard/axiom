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
    			throw new Exception("Not a Valid JSON Input");
    	
    	JSONObject json = (JSONObject) JSONSerializer.toJSON( jsonTxt );  
    	
    	setId(json.getString("id"));
    	setAccessToken(json.getString("access_token"));
    	setIssuedAt(json.getString("issued_at"));
    	setRefreshToken(json.getString("refresh_token"));
    	setInstanceUrl(json.getString("instance_url"));
    	       
    }
    
    public static void main(String orgs[]) throws Exception{
    	
    	String Jtext = "{'id':'https://login.salesforce.com/id/00DA0000000L02pMAC/005A0000000OFZNIA4','issued_at':'1314073536241','refresh_token':'5Aep861Yij7AXt5Ce4hwFBGV6hJVDM3Et5kYhpGuaSq0GucqKfEFu83QvXQPmdsXQ.sln5uG5femw==','instance_url':'https://voore-developer-edition.my.salesforce.com','signature':'iTs6hCmojBviv4wJ/1MSFhRCs1xLuiuEaLWHU/Qc3qI=','access_token':'00DA0000000L02p!ARwAQOjpFxPdmS1QpJxyZvx7sgl.1pigfWn9HZbwhh80r4TfLhOBDL20RWDNXyQZmlUzZWRAkjl.SL_EeZQnDq6.6jwbAqqt'}";
    	
    	OauthJSonParser oj = new OauthJSonParser(Jtext);
    	
    	System.out.println(oj.getId());
    	System.out.println(oj.getAccessToken());
    	System.out.println(oj.getRefreshToken());
    	System.out.println(oj.getInstanceUrl());
    	System.out.println(oj.getIssuedAt());
    	
    	
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