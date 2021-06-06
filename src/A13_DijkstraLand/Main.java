package A13_DijkstraLand;

import java.util.ArrayList;
import java.util.List;

// no Main method as starting point (?)
// therefore just copied and modified from A11_DijkstraPQShortestPath

public class Main {

	public static void main(String[] args) {
		
		ListGraph g = new ListGraph(8, false);
		g.addEdge(0, 4,  3);
		g.addEdge(0, 5,  4);
		g.addEdge(1, 3,  1);
		g.addEdge(1, 4,  6);
		g.addEdge(1, 6,  2);
		g.addEdge(2, 3,  3);
		g.addEdge(2, 4,  4);
		g.addEdge(2, 7,  4);
		g.addEdge(3, 6,  2);
		g.addEdge(3, 7,  1);
		g.addEdge(5, 6,  3);

		String [] lands = {"A", "DE", "CH", "IT", "H", "SLO", "RU", "ES"};
			// 0 -> 4 -> 2 -> 7

		//String [] lands = {"A", "A", "A", "A", "A", "A", "A", "A"}; // -> should be same result as A11_DijkstraPQShortestPath
			// 0 -> 5 -> 6 -> 3 -> 7


		for (int i = 0; i < g.numVertices(); i++) {
			g.setLand(lands[i], i);
		}

		List<Integer> way = Dijkstra.dijkstra(g,0, 7);
		printWay(way);

	}
	
	public static void printWay(List<Integer> way) {
		if (way == null) {
			System.out.println("Kein Weg gefunden.");
			return;
		}
		for (int i=0; i < way.size(); i++) {
			if (i != 0)
				System.out.print(" -> ");
			System.out.print(way.get(i));
		}
	}
}
