package A13_DijkstraLand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dijkstra {

	private static final int BORDER_CROSSING_COST = 1; // constant for cost of border crossing

	public static List<Integer> dijkstra(Graph graph, int from, int to) {

		int[] predecessor = new int[graph.numVertices()];
		int[] distance = new int[graph.numVertices()];

		VertexHeap heap = new VertexHeap();

		for(int i=0; i < graph.numVertices(); i++) {	// loop to add all vertices to heap and set initial values in arrays
			distance[i] = Integer.MAX_VALUE;			// set for all vertices the distance to max integer value
			predecessor[i] = -1;						// set predecessor to -1 (=no predecessor set)
			heap.insert(new Vertex(i, distance[i]));	// add new Vertex / Node to heap and set initial distance to max integer value (from distance array)
		}

		distance[from] = 0;				// set distance for starting node to 0
		heap.setCost(from, 0);		// set cost/priority for starting node to 0 in heap

		//heap.printHeap();

		while (!heap.isEmpty()) {		// loop while heap is not empty
			Vertex v = heap.remove();	// get top element (smallest priority/cost) and remove it from heap

			for (WeightedEdge edge : graph.getEdges(v.getVertex())) {	// iterate through list of edges from current element (removed from top of heap)

				int borderCost = 0;														// reset border cost to 0
				if(!graph.getLand(v.getVertex()).equals(graph.getLand(edge.vertex)))	// check if target is in another country
					borderCost = BORDER_CROSSING_COST;									// set cost for border if border is crossed due different countries

				if((edge.weight + v.getCost() + borderCost) < distance[edge.vertex]){ 	// check if way is better (cost) than existing one
					predecessor[edge.vertex] = v.getVertex();							// set predecessor node
					distance[edge.vertex] = edge.weight + v.getCost() + borderCost;		// distance to target node calculated from edge weight and distance and border cost to current node
					heap.setCost(edge.vertex, distance[edge.vertex]);					// set new priority / cost in heap
				}
			}
		}

		//heap.printHeap();


		// predecessor ausgeben
		for(int i=0; i<predecessor.length; i++) {
			System.out.println(i + " über " + predecessor[i]);
		}

		// Way ausgeben
		System.out.println();
		ArrayList<Integer> way = predToWay(predecessor, from, to);
		return way;
	}
	
	private static ArrayList<Integer> predToWay(int[] predecessor, int from, int to) {

		if(predecessor[to] == -1)	// if predecessor of target (to) is -1 then no path was found
			return null;			// could also be handled through an exception (?)

		ArrayList<Integer> way = new ArrayList<>();
		way.add(to);
		int tmp = to;

		while (tmp != from) {			// go from target (to) through the predecessors until the starting point (from) is reached
			way.add(predecessor[tmp]);
			tmp = predecessor[tmp];
		}
		Collections.reverse(way);	// reverse list to have to and from in the right order
		return way;

	}
	

}
