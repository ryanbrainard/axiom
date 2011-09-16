package axiom.web;

public class About extends AxiomSupport {

    @Override
    public Breadcrumbable getParentPage() {
        return new Home();
    }

}
