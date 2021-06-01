package A09_GraphZusammenRecursive;


import java.util.List;

public class ConnectedComponents {

	private boolean[] visited; // array to maintain visited vertices

	/**
	 * Retourniert die Anzahl der zusammenhängenden Komponenten eines Graphen
	 * @param graph zu prüfender Graph
	 * @return Anzahl der Komponenten
	 */
	public int getNumberOfComponents(Graph graph) {

		int components = 0;							// initialize number of components with 0
		visited = new boolean[graph.numVertices()];		// set size of array - per default all values are set to false

		for (int i = 0; i < graph.numVertices(); i++) {	// run through all vertices of graph
			if (!visited[i]) {						// check if vertex has not been visited yet
				depthFirstSearchRecursive(graph, i);	// start search based on complete graph and unvisited vertex
				components++;						// increase number of connected components by 1
													// method depthFirstSearchRecursive will mark all connected vertices
													// therefore if statement will only be true for the number of components in the graph
			}
		}

		return components;
	}

	private void depthFirstSearchRecursive(Graph graph, int vertex) {

		if (visited[vertex])	// termination of recursive if vertex is already marked as visited in array
			return;

		visited[vertex] = true;									// mark vertex as visited in array

		List<WeightedEdge> edges = graph.getEdges(vertex);		// get all edges from current vertex
		
		for (WeightedEdge edge : edges) {						// loop through edges
			depthFirstSearchRecursive(graph, edge.to_vertex);	// call for each edge DFS method based on
																// complete graph and vertex where the edge was connected to
		}
		
	}




}
