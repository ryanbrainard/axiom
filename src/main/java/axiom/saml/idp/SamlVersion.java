package axiom.saml.idp;

import axiom.web.LabelKeyed;

public enum SamlVersion implements LabelKeyed {
    _1_1("1.1"),
    _2_0("2.0");

    public final String versionString;

    SamlVersion(String versionString) {
        this.versionString = versionString;
    }

    @Override
    public String getLabelKey() {
        return versionString;
    }
}
