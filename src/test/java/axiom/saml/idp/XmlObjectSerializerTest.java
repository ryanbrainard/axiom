package axiom.saml.idp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.opensaml.common.SAMLObjectBuilder;
import org.opensaml.saml1.core.Response;
import org.opensaml.xml.XMLObject;

public class XmlObjectSerializerTest {

	@Test
	@SuppressWarnings("unchecked")
	public void testXmlObjectToString() throws Exception {
		XMLObject xmlObject = ((SAMLObjectBuilder<Response>) AbstractXMLObjectFactory.builderFactory.getBuilder(Response.DEFAULT_ELEMENT_NAME)).buildObject();	 
		assertEquals(
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?><samlp:Response xmlns:samlp=\"urn:oasis:names:tc:SAML:1.0:protocol\" MajorVersion=\"1\" MinorVersion=\"1\"/>", 
				XmlObjectSerializer.xmlObjectToString(xmlObject)
		);
	}

}
