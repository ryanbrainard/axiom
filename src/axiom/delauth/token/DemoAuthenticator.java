package axiom.delauth.token;

public class DemoAuthenticator implements Authenticator {

	@Override
	public boolean authenticate(String username, String password) {
		return password.equals("123456");
	}

}
