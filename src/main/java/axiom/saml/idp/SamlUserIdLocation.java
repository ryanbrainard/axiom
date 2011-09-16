package axiom.saml.idp;

import axiom.web.LabelKeyed;

public enum SamlUserIdLocation implements LabelKeyed {
    SUBJECT,
    ATTRIBUTE;

    public String getLabelKey() {
        return "label.saml.idp.samlUserIdLocation.values." + name().toLowerCase();
    }
}
