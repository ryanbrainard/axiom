package axiom.saml.idp;

import axiom.web.LabelKeyed;

public enum UserType implements LabelKeyed {
    STANDARD,
    PORTAL,
    SITE;

    public String getLabelKey() {
        return "label.saml.idp.userType.values." + name();
    }
}
