package axiom.delauth.token;

import org.apache.log4j.Logger;

import java.util.*;

public class TokenStorePojo implements TokenStore, DemoTokenStore {

    private static Logger logger = Logger.getLogger(TokenStorePojo.class);

    private static Map<String, String> tokenTable = new Hashtable<String, String>();

    public synchronized void addToken(String username, String token) {
        tokenTable.put(username, token);
        logger.info("Added token: " + username + ":" + token);
    }

    public synchronized boolean confirmToken(String username, String token) {
        if (tokenTable.get(username) != null && tokenTable.get(username).equals(token)) {
            logger.info("Positive match for: " + username + ":" + token);
            tokenTable.remove(username);
            logger.info("Removing token for: " + username + ":" + token);
            return true;
        } else {
            logger.info("Negative match for: " + username + ":" + token);
            return false;
        }
    }

    public List<String> getTokenEntries() {
        List<String> tokenEntries = new ArrayList<String>();

        for (String username : tokenTable.keySet()) {
            tokenEntries.add(username + ":" + tokenTable.get(username));
        }

        return tokenEntries;
    }

    public void clearAllTokens() {
        logger.info("Clearing token store.");
        tokenTable.clear();
    }

}
