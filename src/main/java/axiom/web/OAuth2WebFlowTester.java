package axiom.web;

/**
 * @author Ryan Brainard
 * @since 2011-08-22
 */
public class OAuth2WebFlowTester extends OAuthSupport {

    @Override
    public Breadcrumbable getParentPage() {
        return new OAuthHome();
    }
    
}
