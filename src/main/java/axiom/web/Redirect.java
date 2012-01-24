package axiom.web;

public class Redirect extends AxiomSupport {

    @Override
    public Breadcrumbable getParentPage() {
        return new Redirect();
    }

}
