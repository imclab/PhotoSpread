package photoSpread;

import junit.framework.TestCase;
import photoSpread.PhotoSpreadException.*;

public class PhotoSpreadExceptionTest extends TestCase {

	
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	public void testThrow () {

		try {
			throw new IllegalPreferenceException("Testing.");
		} catch (IllegalPreferenceException e) {
			assertEquals(
					"PhotoSpread Exception IllegalPreferenceException(\"Testing.\")",
					"PhotoSpread: Testing.",
					e.getMessage());
		}
	}
}
