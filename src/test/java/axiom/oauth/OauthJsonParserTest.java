package axiom.oauth;
import junit.framework.TestCase;

public class OauthJsonParserTest extends TestCase {
	
	public void testParseOauthJSon() throws Exception{
		
		String Jtext = "{'id':'https://login.salesforce.com/id/00DA0000000L02pMAC/005A0000000OFZNIA4','issued_at':'1314073536241','refresh_token':'5Aep861Yij7AXt5Ce4hwFBGV6hJVDM3Et5kYhpGuaSq0GucqKfEFu83QvXQPmdsXQ.sln5uG5femw==','instance_url':'https://voore-developer-edition.my.salesforce.com','signature':'iTs6hCmojBviv4wJ/1MSFhRCs1xLuiuEaLWHU/Qc3qI=','access_token':'00DA0000000L02p!ARwAQOjpFxPdmS1QpJxyZvx7sgl.1pigfWn9HZbwhh80r4TfLhOBDL20RWDNXyQZmlUzZWRAkjl.SL_EeZQnDq6.6jwbAqqt'}";
    	OauthJSonParser oj = new OauthJSonParser(Jtext);
 
    	assertEquals(oj.getId(), "https://login.salesforce.com/id/00DA0000000L02pMAC/005A0000000OFZNIA4");
		assertEquals(oj.getAccessToken(), "00DA0000000L02p!ARwAQOjpFxPdmS1QpJxyZvx7sgl.1pigfWn9HZbwhh80r4TfLhOBDL20RWDNXyQZmlUzZWRAkjl.SL_EeZQnDq6.6jwbAqqt");
		assertEquals(oj.getRefreshToken(), "5Aep861Yij7AXt5Ce4hwFBGV6hJVDM3Et5kYhpGuaSq0GucqKfEFu83QvXQPmdsXQ.sln5uG5femw==");
		assertEquals(oj.getInstanceUrl(), "https://voore-developer-edition.my.salesforce.com");
		assertEquals(oj.getIssuedAt(), "1314073536241");
		
		
	}
	

}
