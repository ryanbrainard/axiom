package axiom.saml.idp;

import org.apache.log4j.Logger;
import org.opensaml.Configuration;
import org.opensaml.DefaultBootstrap;
import org.opensaml.xml.ConfigurationException;
import org.opensaml.xml.XMLObjectBuilderFactory;

/**
 * Abstract factory used for all types and versions of XML Objects, which 
 * are mostly used as SAML objects.
 * @author rbrainard
 *
 */
public abstract class AbstractXMLObjectFactory {
	private static Logger logger = Logger.getLogger(AbstractXMLObjectFactory.class);

	/**
	 * XMLObjectBuilderFactory from the OpenSAML2 library
	 * used for building all XML Objects
	 */
	final protected static XMLObjectBuilderFactory builderFactory;

	static {
		try {
			logger.debug("Loading OpenSAML2 library");
			DefaultBootstrap.bootstrap();
		} catch (ConfigurationException e) {
			throw new RuntimeException("Error configuring OpenSAML2 library",e);
		} 
		
		logger.debug("Loading OpenSAML BuilderFactory from Configuration");
		builderFactory = Configuration.getBuilderFactory();
		
	}

	/**
	 * No-arg constructor. Should only be created from IdpConfiguration or subclasses.
	 */
	protected AbstractXMLObjectFactory(){}
	
}
