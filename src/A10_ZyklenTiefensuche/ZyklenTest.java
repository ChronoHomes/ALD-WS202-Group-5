package A10_ZyklenTiefensuche;

import static org.junit.Assert.*;
import java.util.List;

import org.junit.Test;

public class ZyklenTest {

	@Test
	public void graphWithOneVertex() {
		Graph g = new ListGraph(1, false);
		Zyklen z = new Zyklen(g);
		assertNull(z.getCycle());
	}

	@Test
	public void graphWithOneEdge() {
		Graph g = new ListGraph(2, false);
		g.addEdge(0, 1);
		Zyklen z = new Zyklen(g);
		assertNull(z.getCycle());
	}

	@Test
	public void connectedRingGraph() {
		Graph g = new ListGraph(5, false);
		g.addEdge(0, 1);
		g.addEdge(1, 2);
		g.addEdge(2, 3);
		g.addEdge(3, 4);
		g.addEdge(4, 0);
		Zyklen z = new Zyklen(g);
		assertCycle(g, 6, z.getCycle());
	}
	
	@Test
	public void miscGraph() {
		Graph g = new ListGraph(8, false);
		g.addEdge(0, 2);
		g.addEdge(2, 7);
		g.addEdge(7, 5);
		g.addEdge(2, 4);
		g.addEdge(2, 6);
		g.addEdge(6, 1);
		g.addEdge(6, 3);
		g.addEdge(1, 3);
		Zyklen z = new Zyklen(g);
		assertCycle(g, 4, z.getCycle());
	}
	
	@Test
	public void multiComponentGraph() {
		Graph g = new ListGraph(9, false);
		g.addEdge(2, 7);
		g.addEdge(7, 5);
		g.addEdge(2, 4);
		g.addEdge(6, 1);
		g.addEdge(6, 3);
		g.addEdge(8, 1);
		g.addEdge(8, 3);
		Zyklen z = new Zyklen(g);
		assertCycle(g, 5, z.getCycle());
	}
	
	@Test
	public void miscGraph2() {
		Graph g = new ListGraph(8, false);
		g.addEdge(0, 1);
		g.addEdge(1, 2);
		g.addEdge(2, 3);
		g.addEdge(3, 4);
		g.addEdge(4, 2);
		g.addEdge(7, 6);
		g.addEdge(6, 5);
		g.addEdge(5, 4);
		Zyklen z = new Zyklen(g);
		assertCycle(g, 4, z.getCycle());
	}

	@Test
	public void directedGraph1() {
		Graph g = new ListGraph(4, true);
		g.addEdge(0, 1);
		g.addEdge(1, 2);
		g.addEdge(2, 3);
		g.addEdge(3, 1);
		Zyklen z = new Zyklen(g);
		assertCycle(g, 4, z.getCycle());
	}

	@Test
	public void directedGraph2() {
		Graph g = new ListGraph(2, true);
		g.addEdge(0, 1);
		g.addEdge(1, 0);
		Zyklen z = new Zyklen(g);
		assertCycle(g, 3, z.getCycle());
	}

	@Test
	public void directedGraph3() {
		Graph g = new ListGraph(4, true);
		g.addEdge(0, 1);
		g.addEdge(1, 2);
		g.addEdge(2, 3);
		g.addEdge(0, 3);
		Zyklen z = new Zyklen(g);
		assertNull(z.getCycle());
	}

	@Test
	public void directedGraph4() {
		Graph g = new ListGraph(5, true);
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(1, 3);
		g.addEdge(2, 3);
		g.addEdge(3, 4);
		Zyklen z = new Zyklen(g);
		assertNull(z.getCycle());
	}

	private void assertCycle(Graph g, int expectedSize, List<Integer> li) {
		assertNotNull(li);
		int n = li.size() - 1;
		assertEquals(expectedSize, li.size());
		assertEquals(li.get(0), li.get(n));		// Start und Ziel müssen gleich sein
		for (int i=0; i < n; i++) {
			assertTrue(g.hasEdge(li.get(i), li.get(i+1)));
		}
	}
}
