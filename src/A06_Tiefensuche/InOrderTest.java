package A06_Tiefensuche;


import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class InOrderTest {

	private Tiefensuche ts;
	
	@Before
	public void setUp() throws Exception {
		ts = new Tiefensuche();
		ts.add(new Film("Stranger than Fiction", 1.88));
		ts.add(new Film("Groundhog Day", 1.68));
		ts.add(new Film("Ferris Bueller's Day Off", 1.7));
		ts.add(new Film("Office Space", 1.42));
		ts.add(new Film("Gattaca", 1.77));
		ts.add(new Film("Pleasantville", 2.07));
		ts.add(new Film("Avanti!", 2.33));
		ts.add(new Film("Alien", 1.95));
		ts.add(new Film("The Great Dictator", 2.06));
		ts.add(new Film("Adaption", 1.9));
	}

	@Test
	public void testGetNodesInOrder1() {
		List<String> ls = ts.getNodesInOrder(ts.find(new Film("Avanti!", 2.33)));
		assertEquals(1, ls.size());
		assertEquals("Avanti!", ls.get(0));
	}

	@Test
	public void testGetNodesInOrder2() {
		List<String> ls = ts.getNodesInOrder(ts.find(new Film("Alien", 1.95)));
		assertEquals(3, ls.size());
		assertEquals("Adaption", ls.get(0));
		assertEquals("Alien", ls.get(1));
		assertEquals("The Great Dictator", ls.get(2));
	}

	@Test
	public void testGetNodesInOrder3() {
		List<String> ls = ts.getNodesInOrder(ts.getRoot());
		assertEquals(10, ls.size());
		assertEquals("Office Space", ls.get(0));
		assertEquals("Groundhog Day", ls.get(1));
		assertEquals("Ferris Bueller's Day Off", ls.get(2));
		assertEquals("Gattaca", ls.get(3));
		assertEquals("Stranger than Fiction", ls.get(4));
		assertEquals("Adaption", ls.get(5));
		assertEquals("Alien", ls.get(6));
		assertEquals("The Great Dictator", ls.get(7));
		assertEquals("Pleasantville", ls.get(8));
		assertEquals("Avanti!", ls.get(9));
	}
}
