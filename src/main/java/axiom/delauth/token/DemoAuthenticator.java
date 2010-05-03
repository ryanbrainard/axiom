package axiom.delauth.token;

public class DemoAuthenticator implements Authenticator {

	public boolean authenticate(String username, String password) {
        return !(null == username || null == password) && password.equals("123456");
    }

}
