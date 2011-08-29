package axiom.delauth.token;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DemoAuthenticatorTest {

    @Test
    public void authenticateTestWithGoodPassword() {

        final String USERNAME = "TestUsername";
        final String PASSWORD = "123456";

        DemoAuthenticator demo = new DemoAuthenticator();
        assertTrue(demo.authenticate(USERNAME, PASSWORD));
    }

    @Test
    public void authenticateTestWithBadPassword() {

        final String USERNAME = "TestUsername";
        final String PASSWORD = "b123456";

        DemoAuthenticator demo = new DemoAuthenticator();
        demo.authenticate(USERNAME, PASSWORD);
        assertFalse(demo.authenticate(USERNAME, PASSWORD));
    }


}
