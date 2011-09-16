package axiom.web;

import axiom.delauth.token.DemoTokenStore;
import axiom.delauth.token.TokenStorePojo;

import java.util.List;


public class TokenStoreViewer extends AxiomSupport {

    private static final long serialVersionUID = 1L;
    private transient DemoTokenStore demoTokenStore = new TokenStorePojo();

    @Override
    public Breadcrumbable getParentPage() {
        return new TokenBasedLogin();
    }

    public List<String> getTokenStoreEntries() {
        return demoTokenStore.getTokenEntries();
    }

    public String clearTokenStore() {
        demoTokenStore.clearAllTokens();
        return SUCCESS;
    }

}
