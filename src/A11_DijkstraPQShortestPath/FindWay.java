package A11_DijkstraPQShortestPath;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class FindWay {
	protected Graph graph;
	protected int[] predecessor;
	
	public FindWay(Graph graph) {
		this.graph = graph;
		this.predecessor = new int[graph.numVertices()];
	}

	/**
	 * Liefert den Weg von (from) nach (to) als Liste zurück
	 * @param from Startknoten
	 * @param to Zielknoten
	 * @return Weg von Start nach Ziel oder null
	 */
	public List<Integer> findWay(int from, int to) {
		initPathSearch();
		if (!calculatePath(from, to)) {
			return null;
		}
		return createWay(from, to);
	}

	/**
	 * Initialisierungsfunktion
	 */
	abstract protected void initPathSearch();

	/**
	 * Berechnungsfunktion für Weg von (from) nach (to)
	 */
	abstract protected boolean calculatePath(int from, int to);
	
	/**
	 * Weg von (to) nach (from) aus Vorgängerknoten rekonstruieren
	 * @param from Startknoten
	 * @param to Zielknoten
	 * @return Weg als Liste
	 */
	protected ArrayList<Integer> createWay(int from, int to) {

		if(predecessor[to] == -1)	// if predecessor of target (to) is -1 then no path was found -> return null
			return null;

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
