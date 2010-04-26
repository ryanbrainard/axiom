package axiom.delauth.token;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;

public class TokenGeneratorTest {
	
	@Test
	public void testTokenGeneratorWithStandardUsername(){
		TokenGenerator generator = new TokenGenerator();
		final String USERNAME = "testUsername";
		final String TOKEN = generator.generateToken(USERNAME);
		
		assertNotNull(TOKEN);
	}

	@Test
	public void testTokenGeneratorWithEmptyUsername(){
		TokenGenerator generator = new TokenGenerator();
		final String USERNAME = "";
		final String TOKEN = generator.generateToken(USERNAME);
		
		assertNotNull(TOKEN);
	}
	
	@Test
	public void testTokenGeneratorWithNullUsername(){
		TokenGenerator generator = new TokenGenerator();
		final String USERNAME = null;
		String TOKEN = null;
		try{
		TOKEN = generator.generateToken(USERNAME);
		fail();
		}catch (NullPointerException npe){
			// expected behavior
		}
	}

}