package axiom.web;


public class SamlIdpHome extends AxiomSupport implements Breadcrumbable {

    @Override
    public Breadcrumbable getParentPage() {
        return new Home();
    }

    public String getSamlIdpServiceEndpoint() {
        return getAxiomEndpoint() + "/services/samlIdp";
    }

}
