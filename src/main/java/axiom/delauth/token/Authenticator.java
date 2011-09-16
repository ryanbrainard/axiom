package axiom.delauth.token;

public interface Authenticator {

    public boolean authenticate(String username, String password);

}
