package A10_ZyklenTiefensuche;

import java.util.LinkedList;
import java.util.List;


public class Zyklen {

	private Graph graph;

	public Zyklen(Graph g) {
		this.graph = g;
	}

	/**
	 * Retourniert einen Zyklus eines Graphen, sofern einer existiert
	 * @param g zu prüfender Graph
	 * @return Anzahl der Komponenten
	 */
	//TODO - LAUFZEIT
	public List<Integer> getCycle() {

















		return null;

		/*
		List<Integer> list = new LinkedList<>();
		int startVertex = 0;


		list.add(startVertex);

		while (startVertex < graph.numVertices()) {

			for (WeightedEdge edge : graph.getEdges(startVertex)) {
				if (!list.contains(edge.to_vertex))
					list.add(edge.to_vertex);
				else
					return list;

				if (!list.contains(edge.from_vertex))
					list.add(edge.from_vertex);
				else
					return list;
			}
			startVertex++;
		}
		return null;
		 */


	}
	
	

}
