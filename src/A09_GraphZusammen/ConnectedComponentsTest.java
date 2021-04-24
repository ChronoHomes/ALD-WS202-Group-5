package A09_GraphZusammen;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConnectedComponentsTest {

	@Test
	public void graphWithOneVertex() {
		Graph g = new ListGraph(1, false);
		ConnectedComponents cc = new ConnectedComponents();
		assertEquals(1, cc.getNumberOfComponents(g));
	}

	@Test
	public void connectedRingGraph() {
		Graph g = new ListGraph(5, false);
		g.addEdge(0, 1);
		g.addEdge(1, 2);
		g.addEdge(2, 3);
		g.addEdge(3, 4);
		g.addEdge(4, 0);
		ConnectedComponents cc = new ConnectedComponents();
		assertEquals(1, cc.getNumberOfComponents(g));
	}
	
	@Test
	public void twoComponents() {
		Graph g = new ListGraph(6, false);
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(0, 3);
		g.addEdge(4, 5);
		ConnectedComponents cc = new ConnectedComponents();
		assertEquals(2, cc.getNumberOfComponents(g));
	}
	
	@Test
	public void totallyDisconnected() {
		Graph g = new ListGraph(6, false);
		ConnectedComponents cc = new ConnectedComponents();
		assertEquals(6, cc.getNumberOfComponents(g));
	}
	
	@Test
	public void threeComponents() {
		Graph g = new ListGraph(9, false);
		g.addEdge(0, 1);
		g.addEdge(1, 2);
		g.addEdge(1, 3);
		g.addEdge(3, 4);
		g.addEdge(4, 2);
		g.addEdge(6, 7);
		g.addEdge(7, 8);
		g.addEdge(8, 6);
		ConnectedComponents cc = new ConnectedComponents();
		assertEquals(3, cc.getNumberOfComponents(g));
	}
	
}
