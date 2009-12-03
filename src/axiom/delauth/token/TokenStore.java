package axiom.delauth.token;

public interface TokenStore {
	void addToken(String username, String token);
	boolean confirmToken(String username, String token);
	void clearAllTokens();
}
