package A13_DijkstraLand;

import java.util.ArrayList;
import java.util.List;

public class Dijkstra {

	public static List<Integer> dijkstra(Graph g, int von, int nach) {
		
		int[] pred = new int[g.numVertices()];
		int[] dist = new int[g.numVertices()];
	
		// TODO
		
		// pred ausgeben
		for(int i=0; i<pred.length; i++) {
			System.out.println(i + " über " + pred[i]);
		}
		
		
		// Way ausgeben
		System.out.println();
		ArrayList<Integer> way = predToWay(pred, von, nach);
		return way;
	}
	
	private static ArrayList<Integer> predToWay(int[] pred, int from, int to) {
		
		ArrayList<Integer> way = new ArrayList<Integer>(); 
		
		// TODO
		
		return way;
	}
	

}
