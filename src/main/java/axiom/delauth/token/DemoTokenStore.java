package axiom.delauth.token;

import java.util.List;

public interface DemoTokenStore extends TokenStore {
    public List<String> getTokenEntries();
}
