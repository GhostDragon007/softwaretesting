package sjh;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class triangleTest {
	private static triangle tri = new triangle();
	
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	
	}

	@Test
	public void testIfEquilateral() {
		tri.setSide(1.0, 1.0, 1.0);
		assertEquals(tri.ifEquilateral(), true);	
	}

	@Test
	public void testIfIsosceles() {
		tri.setSide(1.5, 2.0, 2.0);		
		assertEquals(tri.ifIsosceles(), true);

	}

	@Test
	public void testIfScalene() {
		tri.setSide(1.0, 1.5, 2.0);
		assertEquals(tri.ifScalene(), true);
	}

}
