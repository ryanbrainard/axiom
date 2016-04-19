package axiom.saml.idp;

import org.apache.log4j.Logger;

import java.security.SecureRandom;


/**
 * Generates random IDs for use with XML Objects, namely Assertions and Responses
 *
 * @author rbrainard
 */
public class IdGenerator {
    private static Logger logger = Logger.getLogger(AbstractResponseFactory.class);

    private IdGenerator() {
    } // should not be instantiated

    public static String generateId() {
        String id = "_" + Integer.toHexString(new SecureRandom().nextInt(Integer.MAX_VALUE)) + "-" + Integer.toHexString(new SecureRandom().nextInt(Integer.MAX_VALUE));
        logger.debug("Generated Id: " + id);
        return id;
    }
}
