package A11_DijkstraPQShortestPath;

import java.util.ArrayList;
import java.util.List;

public class DijkstraPQShortestPath extends FindWay {
	private int[] distance;

	public DijkstraPQShortestPath(Graph graph) {
		super(graph);
	}

	/**
	 * Startentfernung initialisieren
	 * 
	 * @param from
	 *            Startknoten
	 */
	protected void initPathSearch() {
		int numv = graph.numVertices();
		distance = new int[numv];
		for (int i = 0; i < numv; i++) {
			distance[i] = 9999; // Summen im Graph dürfen nie mehr ergeben
		}
	}

	/**
	 * Berechnet *alle* kürzesten Wege ausgehend vom Startknoten Setzt dist[]-
	 * und pred[]-Arrays, kein Rückgabewert
	 * 
	 * @param from
	 *            Startknoten
	 */
	protected boolean calculatePath(int from, int to) {

		//PriorityQueue<Integer> heap = new PriorityQueue<>();

		VertexHeap heap = new VertexHeap();

		for (int i = 0; i < graph.numVertices(); i++) {	// loop to add all vertices to heap
			heap.insert(new Vertex(i, distance[i]));	// add new Vertex / Node to heap and set initial distance to 9999 (from distance array)
			predecessor[i] = -1;						// set predecessor to -1
		}
		distance[from] = 0;				// set distance from starting node to 0
		heap.setCost(from, 0);		// set cost/priority for starting node to 0 in heap

		//heap.printHeap();

		while(!heap.isEmpty()){
			Vertex v = heap.remove();	// get top element (smallest priority/cost) and remove it from heap

			for (WeightedEdge edge : graph.getEdges(v.getVertex())) {			// iterate through list of edges from current element (removed from top of heap)
				if((edge.weight + v.getCost()) < distance[edge.to_vertex]) {	// check if way is better (cost) than existing one
					predecessor[edge.to_vertex] = v.getVertex();				// set predecessor node
					distance[edge.to_vertex] = edge.weight + v.getCost();		// distance to target node calculated from edge weight and distance to current node
					heap.setCost(edge.to_vertex, distance[edge.to_vertex]);		// set new priority / cost in heap
				}
			}

		}

		//heap.printHeap(); // shows only dummy element

		return true;


	}


}
