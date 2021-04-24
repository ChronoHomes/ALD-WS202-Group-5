package A05_Breitensuche;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class BreitensucheTest {

	private Breitensuche bs;
	
	@Before
	public void setUp() throws Exception {
		bs = new Breitensuche();
		bs.add(6);
		bs.add(3);
		bs.add(10);
		bs.add(5);
		bs.add(2);
		bs.add(1);
		bs.add(12);
		bs.add(11);
		bs.add(15);
	}

	@Test
	public void getBreadthFirstOrder1() {
		List<Integer> li = bs.getBreadthFirstOrder(bs.find(15));
		assertEquals(1, li.size());
		assertEquals(15, li.get(0).intValue());
	}

	@Test
	public void getBreadthFirstOrder2() {
		List<Integer> li = bs.getBreadthFirstOrder(bs.find(12));
		assertEquals(3, li.size());
		assertEquals(12, li.get(0).intValue());
		assertEquals(11, li.get(1).intValue());
		assertEquals(15, li.get(2).intValue());
	}

	@Test
	public void getBreadthFirstOrder3() {
		List<Integer> li = bs.getBreadthFirstOrder(bs.getRoot());
		assertEquals(9, li.size());
		assertEquals(6, li.get(0).intValue());
		assertEquals(3, li.get(1).intValue());
		assertEquals(10, li.get(2).intValue());
		assertEquals(2, li.get(3).intValue());
		assertEquals(5, li.get(4).intValue());
		assertEquals(12, li.get(5).intValue());
		assertEquals(1, li.get(6).intValue());
		assertEquals(11, li.get(7).intValue());
		assertEquals(15, li.get(8).intValue());
	}
	
	@Test
	public void getBreadthFirstOrderForLevel1() {
		List<Integer> li = bs.getBreadthFirstOrderForLevel(bs.getRoot(), 1);
		assertEquals(1, li.size());
		assertEquals(6, li.get(0).intValue());
	}
	
	@Test
	public void getBreadthFirstOrderForLevel2() {
		List<Integer> li = bs.getBreadthFirstOrderForLevel(bs.getRoot(), 2);
		assertEquals(2, li.size());
		assertEquals(3, li.get(0).intValue());
		assertEquals(10, li.get(1).intValue());
	}

	@Test
	public void getBreadthFirstOrderForLevel3() {
		List<Integer> li = bs.getBreadthFirstOrderForLevel(bs.getRoot(), 3);
		assertEquals(3, li.size());
		assertEquals(2, li.get(0).intValue());
		assertEquals(5, li.get(1).intValue());
		assertEquals(12, li.get(2).intValue());
	}

	@Test
	public void getBreadthFirstOrderForLevel4() {
		List<Integer> li = bs.getBreadthFirstOrderForLevel(bs.find(3), 2);
		assertEquals(2, li.size());
		assertEquals(2, li.get(0).intValue());
		assertEquals(5, li.get(1).intValue());
	}

	@Test
	public void getBreadthFirstOrderForLevel5() {
		List<Integer> li = bs.getBreadthFirstOrderForLevel(bs.find(11), 5);
		assertEquals(0, li.size());
		li = bs.getBreadthFirstOrderForLevel(bs.find(11), 2);
		assertEquals(0, li.size());
	}

}
