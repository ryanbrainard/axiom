package axiom.delauth.token;

import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TokenStorePojoTest {

    private static Logger logger = Logger.getLogger(TokenStorePojoTest.class);
    final String TEST_EMPTY_USERNAME = "";
    final String TEST_USERNAME = "testUser";
    final String TEST_TOKEN = "testToken";
    final String BAD_TOKEN = "badToken";
    final String NULL_TOKEN = null;
    final String NULL_USERNAME = null;
    TokenStore store = new TokenStorePojo();

    @Test
    public void testAddTokenAndConfirmToken() {
        store.clearAllTokens();
        store.addToken(TEST_USERNAME, TEST_TOKEN);
        assertTrue(store.confirmToken(TEST_USERNAME, TEST_TOKEN));
    }

    @Test
    public void testAddTokenAndConfirmTokenFailureUsingIncorrectUsername() {
        store.clearAllTokens();
        // note: adding a null token results in npe
        store.addToken(TEST_USERNAME, TEST_TOKEN);
        assertFalse(store.confirmToken(TEST_USERNAME + "X", TEST_TOKEN));
    }

    @Test
    public void testConfirmTokenWithCorrectUsernameIncorrectToken() {
        store.clearAllTokens();

        store.addToken(TEST_USERNAME, TEST_TOKEN);
        assertFalse(store.confirmToken(TEST_USERNAME, BAD_TOKEN));
    }

    @Test
    public void testAddTokenAndConfirmTokenWithEmptyUserName() {
        store.clearAllTokens();
        store.addToken(TEST_EMPTY_USERNAME, TEST_TOKEN);
        assertTrue(store.confirmToken(TEST_EMPTY_USERNAME, TEST_TOKEN));
        // note: should prolly have a catch for this... there must at least be a username
    }

    @Test
    public void testConfirmTokenWithNullToken() {
        store.clearAllTokens();
        assertFalse(store.confirmToken(TEST_USERNAME, NULL_TOKEN));
        // suggest not allowing confirmation of a null token.
        // maybe throw an npe as well?
    }

    @Test
    public void testConfirmTokenWithNullUsername() {
        store.clearAllTokens();
        try {
            store.confirmToken(NULL_USERNAME, TEST_TOKEN);
            fail();
        } catch (NullPointerException npe) {
        }

        // should handle this better considering you cannot add a null token.
        // maybe throw an npe as well?
    }


    @Test
    public void testAddTokenAndConfirmTokenWithNullUserName() {
        store.clearAllTokens();
        try {
            store.addToken(NULL_USERNAME, TEST_TOKEN);
            fail("Should throw NPE if username is null");
        } catch (NullPointerException e) {
            // expected behavior : agreed there should be better handling of this
        }
    }

    @Test
    public void testGetTokenEntries() throws Exception {
        TokenStorePojo store = new TokenStorePojo();
        final int LIST_SIZE = 3;

        store.clearAllTokens();
        assertTrue("store was not properly cleared.", 0 == store.getTokenEntries().size());
        generateTestTokens(LIST_SIZE, store, TEST_USERNAME, TEST_TOKEN);

        List<String> entries = store.getTokenEntries();
        logger.info("stage 1 store.getTokenEntries():" + entries.size());
        assertEquals(LIST_SIZE, entries.size());

        for (int i = 0; i < entries.size(); i++) {
            assertTrue(store.confirmToken(TEST_USERNAME + i, TEST_TOKEN));
        }
        logger.info("stage 2 store.getTokenEntries():" + entries.size());
        // logger info to see if the store is cleared when confirmToken is used.
    }

    @Test
    public void testClearAllTokens() throws Exception {
        TokenStorePojo store = new TokenStorePojo();
        final int LIST_SIZE = 3;

        generateTestTokens(LIST_SIZE, store, TEST_USERNAME, TEST_TOKEN);

        List<String> entries = store.getTokenEntries();

        for (int i = 0; i < entries.size(); i++) {
            assertTrue(store.confirmToken(TEST_USERNAME + i, TEST_TOKEN));
        }
        store.clearAllTokens();
        entries = store.getTokenEntries();

        assertTrue(entries.size() == 0);
    }

    private void generateTestTokens(int numberOfTokens, TokenStore store, String TEST_USERNAME, String TEST_TOKEN) throws Exception {
        if (store == null) {
            throw new Exception("ERROR: Toke store is null.");
        }

        if (numberOfTokens >= 0) {
            for (int i = 0; i < numberOfTokens; i++) {
                store.addToken(TEST_USERNAME + i, TEST_TOKEN);
            }
        }
        // no action is performed if the number of tokens is below 1.
    }
}
