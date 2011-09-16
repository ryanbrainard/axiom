package axiom.saml.idp;

import org.apache.log4j.Logger;
import org.opensaml.Configuration;
import org.opensaml.xml.XMLObject;
import org.opensaml.xml.io.*;
import org.w3c.dom.Element;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

/**
 * Serializes XML Objects into XML Strings
 *
 * @author rbrainard
 */
public class XmlObjectSerializer {

    private static Logger logger = Logger.getLogger(XmlObjectSerializer.class);

    private XmlObjectSerializer() {
    } //should not be instantiated

    /**
     * Serializes XML Objects into XML Strings
     *
     * @param xmlObject
     * @throws MarshallingException
     * @throws TransformerFactoryConfigurationError
     *
     * @throws TransformerException
     */
    public static String xmlObjectToString(XMLObject xmlObject) throws MarshallingException, TransformerFactoryConfigurationError, TransformerException {
        logger.debug("Marshalling XMLObject into Element");
        MarshallerFactory marshallerFactory = Configuration.getMarshallerFactory();
        Marshaller marshaller = marshallerFactory.getMarshaller(xmlObject);
        Element samlObjectElement = null;
        samlObjectElement = marshaller.marshall(xmlObject);

        logger.debug("Transforming Element into String");
        Transformer transformer = null;
        transformer = TransformerFactory.newInstance().newTransformer();

        StreamResult result = new StreamResult(new StringWriter());
        DOMSource source = new DOMSource(samlObjectElement);
        transformer.transform(source, result);

        logger.trace("Completed XML String:\n" + result.getWriter().toString());
        logger.debug("Returning XML String");
        return result.getWriter().toString();
    }

}