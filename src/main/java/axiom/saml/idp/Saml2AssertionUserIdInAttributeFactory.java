package axiom.saml.idp;

import org.apache.log4j.Logger;
import org.opensaml.Configuration;
import org.opensaml.common.SAMLObjectBuilder;
import org.opensaml.saml2.core.*;
import org.opensaml.xml.schema.XSString;
import org.opensaml.xml.schema.impl.XSStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Concrete class that builds a SAML 2.0 assertion with the UserId
 * (Salesforce username or Federation Id) in an Attribute Statement.
 *
 * @author rbrainard
 */
public class Saml2AssertionUserIdInAttributeFactory extends Saml2AbstractAssertionFactory {

    private static Logger logger = Logger.getLogger(Saml2AssertionUserIdInAttributeFactory.class);

    private String attributeName;
    private String attributeUri;

    /**
     * No-arg constructor. Should only be created from IdpConfiguration or subclasses.
     */
    protected Saml2AssertionUserIdInAttributeFactory() {
    }


    @Override
    protected AttributeStatement buildAttributeStatement() throws IllegalStateException {

        logger.debug("Building Attribute Value");
        XSString attributeValue = ((XSStringBuilder) Configuration.getBuilderFactory().getBuilder(XSString.TYPE_NAME)).buildObject(AttributeValue.DEFAULT_ELEMENT_NAME, XSString.TYPE_NAME);
        if (getUserId() != null) {
            logger.debug("Building XSStringBuilder for AttributeValue");
            attributeValue.setValue(getUserId());
            logger.debug("attributeValue set to: " + getUserId());
        } else {
            throw new IllegalStateException("User Id must not be null in Attribute Value");
        }

        logger.debug("Building Attribute");
        Attribute attribute = ((SAMLObjectBuilder<Attribute>) builderFactory.getBuilder(Attribute.DEFAULT_ELEMENT_NAME)).buildObject();
        if (attributeName != null) {
            attribute.setName(attributeName);
            logger.debug("attributeName set to: " + attributeName);
        } else {
            throw new IllegalStateException("Attribute Name must not be null");
        }

        if (attributeUri != null) {
            attribute.setNameFormat(attributeUri);
            logger.debug("Attribute NameFormat from attributeUri set to: " + attributeUri);
        } else {
            throw new IllegalStateException("Atribute URI must not be null.");
        }

        attribute.getAttributeValues().add(attributeValue);

        logger.debug("Adding attribute to attributeList");
        List<Attribute> attributeList = new ArrayList<Attribute>();
        attributeList.add(attribute);

        logger.debug("Calling back to superclass to add standard attributes");
        return super.buildAttributeStatement(attributeList);
    }

    /**
     * Used for building a "null" subject because Salesforce
     * requires a subject even if attribute if being used
     * for the userId. This implements the abstract non-arg method in
     * the super class and then calls back to the arg method in the
     * super class to provide a name id value. Cannot be overriden.
     */
    @Override
    final protected Subject buildSubject() {
        logger.debug("Building null subject");
        return super.buildSubject("NO_SUBJECT");
    }


    public String getAttributeName() {
        return attributeName;
    }


    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }


    public String getAttributeUri() {
        return attributeUri;
    }


    public void setAttributeUri(String attributeUri) {
        this.attributeUri = attributeUri;
    }

}
