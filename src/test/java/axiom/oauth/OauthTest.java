package axiom.oauth;

import junit.framework.TestCase;
import axiom.oauth.OauthRequester;

public class OauthTest extends TestCase{
	
	OauthRequester oReq;
	
public void testAuthorizationHostString() throws Exception{
		
	prepareTestData();
	assertEquals(oReq.generateAuthorizationHostString(),"https://login.salesforce.com/services/oauth2/authorize?client_id=3MVG9yZ.WNe6byQDPNlouJ_iU_a.qAhlAXbgNFEI1iz6XekYCF2zN0_tUk9Ze_cODroFkDlEakiyifyiC1UY6&redirect_uri=http%3a%2f%2flocalhost%3a8080%2faxiom%2foAuth&response_type=code&state=state");

	}

public void testAccessHostString() throws Exception{
	
	prepareTestData();
	oReq.setAuthorizationCode("aPrxZibfVBKPF9vRx87yTPW8qoLH50X6iFgh2KUmIrABvORgd4yXZV7lGUkzgWdGzWph.5hDUQ==");
	oReq.setClientSecret("4219407934265749743");
	
	assertEquals(oReq.generateAccessHostString(),"https://login.salesforce.com/services/oauth2/token?&grant_type=authorization_code&client_id=3MVG9yZ.WNe6byQDPNlouJ_iU_a.qAhlAXbgNFEI1iz6XekYCF2zN0_tUk9Ze_cODroFkDlEakiyifyiC1UY6&redirect_uri=http%3a%2f%2flocalhost%3a8080%2faxiom%2foAuth&code=aPrxZibfVBKPF9vRx87yTPW8qoLH50X6iFgh2KUmIrABvORgd4yXZV7lGUkzgWdGzWph.5hDUQ==&client_secret=4219407934265749743");
		
	}

//TODO : Need to add the test for oAuth Cycle
public void testOauthDance() throws Exception{
	prepareTestData();
	oReq.setAuthorizationCode("aPrxZibfVBKPF9vRx87yTPW8qoLH50X6iFgh2KUmIrABvORgd4yXZV7lGUkzgWdGzWph.5hDUQ==");
	oReq.setClientSecret("4219407934265749743");
	
	//TODO
	
	//	oReq.generateAccessToken();
	//	System.out.println(oReq.getjSonResponse());


}


    private void prepareTestData(){
    	oReq = new OauthRequester();

    	oReq.setHost("https://login.salesforce.com");
    	oReq.setResponseType("code");
    	oReq.setClientKey("3MVG9yZ.WNe6byQDPNlouJ_iU_a.qAhlAXbgNFEI1iz6XekYCF2zN0_tUk9Ze_cODroFkDlEakiyifyiC1UY6");
    	oReq.setCallbackUri("http%3a%2f%2flocalhost%3a8080%2faxiom%2foAuth");
    	oReq.setState("state");
    		
    	
    }




}
