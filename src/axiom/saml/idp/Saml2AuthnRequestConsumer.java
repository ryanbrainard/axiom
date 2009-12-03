package axiom.saml.idp;


public class Saml2AuthnRequestConsumer {

//	private AuthnRequest authnRequest;
//	private String assertionConsumerServiceURL;
	
	public static String parseAssertionConsumerServiceURL(String authnRequestXmlString){
		String startText = "AssertionConsumerServiceURL=\"";
		String endText = "\"";
		
		int startIndex = authnRequestXmlString.indexOf(startText) + startText.length();
		return authnRequestXmlString.substring(startIndex,authnRequestXmlString.indexOf(endText,startIndex));
	}

//	public String getAssertionConsumerServiceURL() {
//		return assertionConsumerServiceURL;
//	}
//
//	public void setAssertionConsumerServiceURL(String assertionConsumerServiceURL) {
//		this.assertionConsumerServiceURL = assertionConsumerServiceURL;
//	}
	
	
	
}
