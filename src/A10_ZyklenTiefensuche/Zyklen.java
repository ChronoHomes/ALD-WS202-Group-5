package A10_ZyklenTiefensuche;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Zyklen {

	private Graph graph;
	private boolean visited[];	// array to maintain visited vertices
	private HashMap<Integer, Integer> predecessorMap = new HashMap<>();


	public Zyklen(Graph g) {
		this.graph = g;
	}
	/**
	 * Retourniert einen Zyklus eines Graphen, sofern einer existiert
	 * @param g zu pr?fender Graph
	 * @return Anzahl der Komponenten
	 */
	public List<Integer> getCycle() {

		if (graph.numVertices() <= 2 && !graph.isDirected()) // no cycle possible if vertices count smaller or equal to 2 and if graph is undirected
			return null;

		visited = new boolean[graph.numVertices()];		// set size of array - per default all values are set to false

		for (int i=0; i < graph.numVertices(); i++) {	// loop through all vertices in graph
			if (!visited[i]) {							// check if vertex has not been visited yet
				List<Integer> cycleWithinGraph = recursiveGetCycle(i, -1); // call of recursive method
				if (cycleWithinGraph != null) { 		// cycle found
					System.out.println(cycleWithinGraph);
					return cycleWithinGraph;
				}
			}
		}
		return null; // no cycle found -> return null
	}
	/**
	 * Rekurisve Funktion zum Suchen des Zyklus
	 * @param vertex Aktueller Knoten
	 * @param predecessor Vorg?nger
	 * @return Zyklus oder null
	 */



	private List<Integer> recursiveGetCycle(int vertex, int predecessor) {

		//TODO - improve variable naming

		List<Integer> cycleGraph = new ArrayList<>();

		if (predecessorMap.containsKey(vertex)) {
			Integer tmp = vertex;

			while (tmp != null){
				cycleGraph.add(tmp);
				tmp = predecessorMap.get(tmp);
			}
			cycleGraph.add(vertex);
			System.out.println("cycleGraph" + cycleGraph);
			return cycleGraph;
		}


		if (visited[vertex])	// node already visited
			return null;

		visited[vertex] = true;


		predecessorMap.put(predecessor, vertex); // add predecessor to list

		for (WeightedEdge edge : graph.getEdges(vertex)) { // loop through all edges
			if (edge.to_vertex == predecessor && !graph.isDirected()) { // undirected graph
				continue;
			}
			List<Integer> cycle = recursiveGetCycle(edge.to_vertex, vertex); // recursive call
			if (cycle != null) { // cycle found
				System.out.println("cycle" + cycle);
				return cycle;
			}
		}

		predecessorMap.remove(predecessor); // remove predecessor -> backtracking
		System.out.println("end of recursive method");
		return null; // no cycle found -> return null
	}


}
