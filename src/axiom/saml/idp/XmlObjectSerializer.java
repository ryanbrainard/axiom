package axiom.saml.idp;

import java.io.StringWriter;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.opensaml.Configuration;
import org.opensaml.xml.XMLObject;
import org.opensaml.xml.io.*;
import org.w3c.dom.Element;

/**
 * Serializes XML Objects into XML Strings
 * @author rbrainard
 *
 */
public class XmlObjectSerializer {
	
	private static Logger logger = Logger.getLogger(XmlObjectSerializer.class);
	
	private XmlObjectSerializer(){} //should not be instantiated
	
	/**
	 * Serializes XML Objects into XML Strings
	 * @param xmlObject
	 */
	public static String xmlObjectToString(XMLObject xmlObject){
		logger.debug("Marshalling XMLObject into Element");
		MarshallerFactory marshallerFactory = Configuration.getMarshallerFactory();
		Marshaller marshaller = marshallerFactory.getMarshaller(xmlObject);
		Element samlObjectElement = null;
		try {
			samlObjectElement = marshaller.marshall(xmlObject);
		} catch (MarshallingException e) {
			logger.error(e);
		}
		
		logger.debug("Transforming Element into String");
		Transformer transformer = null;
		try {
			transformer = TransformerFactory.newInstance().newTransformer();
		} catch (TransformerConfigurationException e) {
			logger.error(e);
		} catch (TransformerFactoryConfigurationError e) {
			logger.error(e);
		}
		StreamResult result = new StreamResult(new StringWriter());
		DOMSource source = new DOMSource(samlObjectElement);
		try {
			transformer.transform(source, result);
		} catch (TransformerException e) {
			logger.error(e);
		}

		logger.trace("Completed XML String:\n" + result.getWriter().toString());
		logger.debug("Returning XML String");
		return result.getWriter().toString();
	}

}
