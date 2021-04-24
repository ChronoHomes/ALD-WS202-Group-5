package A06_Tiefensuche;

import java.util.List;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MinMaxTest {
	
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
	public void getMinMaxSingleResult() {
		List<String> ls = ts.getMinMaxPreOrder(1.0, 1.5);
		assertEquals(1, ls.size());
		assertEquals("Office Space", ls.get(0));
	}

	@Test
	public void getMinMaxSomeResults1() {
		List<String> ls = ts.getMinMaxPreOrder(1.75, 1.92);
		assertEquals(3, ls.size());
		assertEquals("Stranger than Fiction", ls.get(0));
		assertEquals("Gattaca", ls.get(1));
		assertEquals("Adaption", ls.get(2));
	}

	@Test
	public void getMinMaxSomeResults2() {
		List<String> ls = ts.getMinMaxPreOrder(1.92, 2.1);
		assertEquals(3, ls.size());
		assertEquals("Pleasantville", ls.get(0));
		assertEquals("Alien", ls.get(1));
		assertEquals("The Great Dictator", ls.get(2));
	}

	@Test
	public void getMinMaxAlmostAllResults() {
		List<String> ls = ts.getMinMaxPreOrder(1.69, 2.1);
		assertEquals(7, ls.size());
		assertEquals("Stranger than Fiction", ls.get(0));
		assertEquals("Ferris Bueller's Day Off", ls.get(1));
		assertEquals("Gattaca", ls.get(2));
		assertEquals("Pleasantville", ls.get(3));
		assertEquals("Alien", ls.get(4));
		assertEquals("Adaption", ls.get(5));
		assertEquals("The Great Dictator", ls.get(6));
	}

	@Test
	public void getMinMaxNoResult() {
		List<String> ls = ts.getMinMaxPreOrder(1.0, 1.2);
		assertEquals(0, ls.size());
		ls = ts.getMinMaxPreOrder(2.4, 2.5);
		assertEquals(0, ls.size());
	}
}
